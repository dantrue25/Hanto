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

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 */
public class HantoPieceImpl implements HantoPiece {

	private HantoPlayerColor color;
	private HantoPieceType type;
	
	/**
	 * Constructor for HantoPieceImpl.
	 * @param type HantoPieceType
	 * @param color HantoPlayerColor
	 */
	public HantoPieceImpl( HantoPieceType type, HantoPlayerColor color ) {
		this.color = color;
		this.type = type;
	}
	
	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	@Override
	public HantoPieceType getType() {
		return type;
	}

}
