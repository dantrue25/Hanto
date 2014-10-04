/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentdbtrue.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.rules.GameRule;
import hanto.studentdbtrue.common.rules.base.CantMovePieceThatIsntOnBoard;
import hanto.studentdbtrue.common.rules.base.FirstMoveMustBeAtOrigin;
import hanto.studentdbtrue.common.rules.base.MustHavePieceToPlayIt;
import hanto.studentdbtrue.common.rules.base.MustPlayButterflyByFourthTurn;
import hanto.studentdbtrue.common.rules.base.OnlyMovePieceOfYourColor;
import hanto.studentdbtrue.common.rules.base.PieceMustBeAdjacentToAnother;
import hanto.studentdbtrue.common.rules.base.PieceTypeAndToMustBeValidIfNotResigning;
import hanto.studentdbtrue.common.rules.base.PieceTypeMustBeCorrectToMoveIt;
import hanto.studentdbtrue.common.rules.base.SpaceMustNotAlreadyBeOccupied;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan
 *
 */
public abstract class BaseHantoGame implements HantoGame {

	protected Board board;
	protected int turnNum = 1;
	protected HantoPlayerColor movesFirst;
	protected PlayerState bluePlayer = new PlayerState(HantoPlayerColor.BLUE);
	protected PlayerState redPlayer = new PlayerState(HantoPlayerColor.RED);
	protected PlayerState currentPlayer;
	protected List<GameRule> ruleSet;
	private boolean gameOver;
	protected boolean redResigns;
	protected boolean blueResigns;
	protected List<Movement> movementList;
	
	/**
	 * Constructor for BaseHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
	protected BaseHantoGame (HantoPlayerColor movesFirst) {
		
		board = new Board();
		
		this.movesFirst = movesFirst;
		if (movesFirst == HantoPlayerColor.BLUE) {
			currentPlayer = bluePlayer;
		}
		else {
			currentPlayer = redPlayer;
		}
		
		setUpBaseRuleSet();
	}
	
	/**
	 * @throws HantoException 
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);
		HantoPieceImpl p = new HantoPieceImpl(pieceType, getCurrentPlayer().getColor());
		
		// Check to see if any of the rules are broken
		for (GameRule r : ruleSet) {
			r.check(this, board, pieceType, to, from);
		}
		
		checkForResignation();
		
		if (!gameOver) {			
			if (from != null) {
				board.removePieceOn(myFrom);
			}
			else {
				currentPlayer.removePiece(pieceType);
			}
			
			// Put piece onto the board
			board.putPieceOn(myTo, p);
		}
		
		// Increment turn when needed
		if(currentPlayer.getColor() != movesFirst) {
			turnNum++;
		}
				
		switchPlayers();
				
		return getMoveResult();
		
	}
	
	/**
	 * Method getMoveResult.
	
	 * @return MoveResult */
	public MoveResult getMoveResult () {
		MoveResult result;
		
		if(board.isSurrounded(board.blueBLoc) && board.isSurrounded(board.redBLoc)) {
			result = MoveResult.DRAW;
			setGameOver(true);
		}
		else if(board.isSurrounded(board.blueBLoc) || blueResigns) {
			result = MoveResult.RED_WINS;
			setGameOver(true);
		}
		else if(board.isSurrounded(board.redBLoc) || redResigns) {
			result = MoveResult.BLUE_WINS;
			setGameOver(true);
		}
		else {
			result = MoveResult.OK;
		}
		
		return result;
	}
	
	private void checkForResignation () {
		if (blueResigns || redResigns) {
			gameOver = true;
		}
	}
	
	// Add rules to the base game
	private void setUpBaseRuleSet () {
		ruleSet = new ArrayList<GameRule>();
		
		ruleSet.add(new PieceTypeAndToMustBeValidIfNotResigning());
		ruleSet.add(new OnlyMovePieceOfYourColor());
		ruleSet.add(new MustPlayButterflyByFourthTurn());
		ruleSet.add(new CantMovePieceThatIsntOnBoard());
		ruleSet.add(new FirstMoveMustBeAtOrigin());
		ruleSet.add(new SpaceMustNotAlreadyBeOccupied());
		ruleSet.add(new PieceMustBeAdjacentToAnother());
		ruleSet.add(new MustHavePieceToPlayIt());
		ruleSet.add(new PieceTypeMustBeCorrectToMoveIt());
	}
	
	/**
	 * @param p
	
	
	 * @return Movement */
	public Movement getMovementType (HantoPieceType p) {
		Movement movement = null;
		
		for (Movement m : movementList) {
			if (m.getPieceType() == p) {
				movement = m;
			}
		}
		
		return movement;
		
	}

	@Override
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
	
	/**
	 * Method switchPlayers.
	 */
	protected void switchPlayers () {
		if (currentPlayer == bluePlayer) {
			currentPlayer = redPlayer;
		}
		else {
			currentPlayer = bluePlayer;
		}
	}
	
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return board.getPieceAt(where);
	}

	/**
	 * @return the turnNum
	 */
	public int getTurnNum() {
		return turnNum;
	}

	/**
	 * @return the currentPlayer
	 */
	public PlayerState getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * @param color
	 */
	public void setCurrentPlayer(HantoPlayerColor color) {
		if (color == HantoPlayerColor.BLUE) {
			currentPlayer = bluePlayer;
		}
		else {
			currentPlayer = redPlayer;
		}
	}

	/**
	 * @return the isGameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param isGameOver the isGameOver to set
	 */
	public void setGameOver(boolean isGameOver) {
		gameOver = isGameOver;
	}
	
	/**
	 * 
	 */
	public void makeRedResign () {
		redResigns = true;
	}
	
	/**
	 * 
	 */
	public void makeBlueResign () {
		blueResigns = true;
	}

}
