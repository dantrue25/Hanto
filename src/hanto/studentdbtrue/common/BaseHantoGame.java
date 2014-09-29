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
import hanto.studentdbtrue.common.rules.CantMovePieceThatIsntOnBoard;
import hanto.studentdbtrue.common.rules.FirstMoveMustBeAtOrigin;
import hanto.studentdbtrue.common.rules.GameRule;
import hanto.studentdbtrue.common.rules.MustHavePieceToPlayIt;
import hanto.studentdbtrue.common.rules.MustPlayButterflyByFourthTurn;
import hanto.studentdbtrue.common.rules.PieceMustBeAdjacentToAnother;
import hanto.studentdbtrue.common.rules.SpaceMustNotAlreadyBeOccupied;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan
 *
 */
public abstract class BaseHantoGame implements HantoGame {

	private Board board;
	protected int turnNum = 1;
	protected HantoPlayerColor movesFirst;
	protected PlayerState bluePlayer = new PlayerState(HantoPlayerColor.BLUE);
	protected PlayerState redPlayer = new PlayerState(HantoPlayerColor.RED);
	protected PlayerState currentPlayer;
	protected List<GameRule> ruleSet;
	
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
		HantoPieceImpl p = new HantoPieceImpl(pieceType, getCurrentPlayer().getColor());
		
		// Check to see if any of the rules are broken
		for (GameRule r : ruleSet) {
			r.check(this, board, pieceType, myTo, from);
		}		
		
		// Put piece onto the board, and remove piece from player
		board.putPieceOn(myTo, p);
		currentPlayer.removePiece(pieceType);
		
		// Increment turn when needed
		if(currentPlayer.getColor() != movesFirst) {
			turnNum++;
		}
				
		switchPlayers();
				
		return board.getMoveResult(bluePlayer, redPlayer);
		
	}
	
	// Add rules to the base game
	private void setUpBaseRuleSet () {
		ruleSet = new ArrayList<GameRule>();
		
		ruleSet.add(new MustPlayButterflyByFourthTurn());
		ruleSet.add(new CantMovePieceThatIsntOnBoard());
		ruleSet.add(new FirstMoveMustBeAtOrigin());
		ruleSet.add(new SpaceMustNotAlreadyBeOccupied());
		ruleSet.add(new PieceMustBeAdjacentToAnother());
		ruleSet.add(new MustHavePieceToPlayIt());
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

}
