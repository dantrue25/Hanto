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
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.BaseHantoGame;

/**
 * @author Dan
 *
 */
public class GammaHantoGame extends BaseHantoGame implements HantoGame {

	/**
	 * 
	 */
	public GammaHantoGame () {
		
	}
	
	@Override
	public MoveResult makeMove (HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		if (!isContinuous (from, to)) {
			throw new HantoException ("Move makes board not continuous.");
		}
		
		return super.makeMove(pieceType, from, to);
	}
	
	private boolean isContinuous (HantoCoordinate from, HantoCoordinate to) {
		
		
		return false;
	}

}
