/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentdbtrue.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.*;

/**
 */
public class BetaHantoGame extends BaseHantoGame implements HantoGame {
	
	/**
	 * Constructor for BetaHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		this.movesFirst = movesFirst;
		
		if (movesFirst == HantoPlayerColor.BLUE) {
			currentPlayer = bluePlayer;
		}
		else {
			currentPlayer = redPlayer;
		}
		
		setUpPlayers();
	}
	
	private void setUpPlayers () {
		bluePlayer.addPiece(HantoPieceType.BUTTERFLY);
		redPlayer.addPiece(HantoPieceType.BUTTERFLY);
		
		for (int i = 0; i < 5; i++) {
			bluePlayer.addPiece(HantoPieceType.SPARROW);
			redPlayer.addPiece(HantoPieceType.SPARROW);
		}
	}

	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoPieceImpl p = new HantoPieceImpl(pieceType, currentPlayer.getColor());
		
		// Make sure piece is placed, not moved
		if (from != null) {
			throw new HantoException ("Cannot move a piece.");
		}
		// Make sure first move is to (0, 0)
		if (board.isEmpty() && !myTo.equals(new HantoCoordinateImpl(0, 0))) {
			throw new HantoException ("First move must be to (0, 0).");
		}
		// Make sure move isn't to an occupied space
		if (board.get(to) != null) {
			throw new HantoException ("A piece is already in that location.");
		}
		// Make sure that player uses a butterfly by turn 4
		if (turnNum == 4 && currentPlayer.hasPieceOfType(HantoPieceType.BUTTERFLY) && pieceType != HantoPieceType.BUTTERFLY) {
			throw new HantoException ("Must play the butterfly by 4th turn.");
		}
		// Make sure new piece is adjacent to some piece on the board
		if (!isAdjacent(myTo) && !board.isEmpty()) {
			throw new HantoException ("Piece isn't adjacent to an existing piece.");
		}
		// Make sure player has the piece they are trying to place
		if (!currentPlayer.hasPieceOfType(pieceType)) {
			throw new HantoException ("Current player doesn't have any of those pieces.");
		}
		// If piece is butterfly, record the location
		if (pieceType == HantoPieceType.BUTTERFLY) {
			if(currentPlayer.getColor() == HantoPlayerColor.BLUE) {
				blueBLoc = myTo;
			}
			else {
				redBLoc = myTo;						
			}
		}
		
		// Put piece onto the board, and remove piece from player
		board.put(myTo, p);
		currentPlayer.removePiece(pieceType);
			
		if(currentPlayer.getColor() != movesFirst) {
			turnNum++;
		}
		
		switchPlayers();
		
		return getMoveResult();
	}
	
	private MoveResult getMoveResult () {
		MoveResult result;
		
		if(isSurrounded(blueBLoc) && isSurrounded(redBLoc)) {
			result = MoveResult.DRAW;
		}
		else if(isSurrounded(blueBLoc)) {
			result = MoveResult.RED_WINS;
		}
		else if(isSurrounded(redBLoc)) {
			result = MoveResult.BLUE_WINS;
		}
		else if(bluePlayer.hasNoPieces() && redPlayer.hasNoPieces()) {
			result = MoveResult.DRAW;
		}
		else {
			result = MoveResult.OK;
		}
		
		return result;
	}

}
