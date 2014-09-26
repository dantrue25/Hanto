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
		
		// Make sure piece is placed, not moved
		if (from != null) {
			throw new HantoException ("Cannot move a piece.");
		}
		
		return super.makeMove(pieceType, from, to);
	}

}
