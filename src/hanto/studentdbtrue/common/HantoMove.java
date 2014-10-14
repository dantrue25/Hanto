/**
 * 
 */
package hanto.studentdbtrue.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;

/**
 * @author Dan
 *
 */
public class HantoMove {

	private HantoPieceType pieceType;
	private HantoCoordinate from;
	private HantoCoordinate to;
	
	/**
	 * 
	 * @param p
	 * @param f
	 * @param t
	 */
	public HantoMove (HantoPieceType p, HantoCoordinate f, HantoCoordinate t) {
		pieceType = p;
		from = f;
		to = t;
	}

	/**
	 * @return the pieceType
	 */
	public HantoPieceType getPieceType() {
		return pieceType;
	}

	/**
	 * @return the from
	 */
	public HantoCoordinate getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public HantoCoordinate getTo() {
		return to;
	}

}
