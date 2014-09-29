/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentdbtrue.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.rules.CanOnlyWalkOneSpace;
import hanto.studentdbtrue.common.rules.CantMoveIfBlocked;
import hanto.studentdbtrue.common.rules.MustBeContinuous;
import hanto.studentdbtrue.common.rules.NewPieceMustBeAdjacentToOwnColor;

/**
 * @author Dan
 *
 */
public class GammaHantoGame extends BaseHantoGame implements HantoGame {

	boolean gameOver = false;
	/**
	 * 
	 */
	public GammaHantoGame (HantoPlayerColor movesFirst) {
		super(movesFirst);
		setUpPlayers();
		setUpAdditionalRules();
	}
	
	private void setUpPlayers () {
		bluePlayer.addPiece(HantoPieceType.BUTTERFLY);
		redPlayer.addPiece(HantoPieceType.BUTTERFLY);
		
		for (int i = 0; i < 5; i++) {
			bluePlayer.addPiece(HantoPieceType.SPARROW);
			redPlayer.addPiece(HantoPieceType.SPARROW);
		}
	}
	
	private void setUpAdditionalRules () {
		ruleSet.add(new MustBeContinuous());
		ruleSet.add(new NewPieceMustBeAdjacentToOwnColor());
		ruleSet.add(new CantMoveIfBlocked());
		ruleSet.add(new CanOnlyWalkOneSpace());
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		MoveResult r;
		
		if (pieceType == null && from == null && to == null) {
			gameOver = true;
			r = MoveResult.DRAW;
		}
		
		if (!gameOver) {
			r = super.makeMove(pieceType, from, to);
			if (r == MoveResult.OK && turnNum == 21) {
				r = MoveResult.DRAW;
			}
			if (r == MoveResult.DRAW || r == MoveResult.BLUE_WINS || r == MoveResult.RED_WINS) {
				gameOver = true;
			}
		}
		else {
			throw new HantoException("Cannot move after game is over.");
		}
		return r;
	}

}
