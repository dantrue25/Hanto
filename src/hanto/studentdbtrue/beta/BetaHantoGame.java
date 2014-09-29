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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.common.*;
import hanto.studentdbtrue.common.rules.CantMoveAnyPiece;

/**
 */
public class BetaHantoGame extends BaseHantoGame {
	
	/**
	 * Constructor for BetaHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		setUpAdditionalRules();
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
	
	private void setUpAdditionalRules () {
		ruleSet.add(new CantMoveAnyPiece());
	}

}
