/**
 * 
 */
package hanto.studentdbtrue.common;

import hanto.common.HantoPieceType;

/**
 * @author Dan
 *
 */
public class Movement {
	private HantoPieceType pieceType;
	private HantoMovementType moveType;
	private int distance;
	
	/**
	 * @param p
	 * @param m
	 * @param d
	 */
	public Movement (HantoPieceType p, HantoMovementType m, int d) {
		pieceType = p;
		moveType = m;
		distance = d;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return the type
	 */
	public HantoMovementType getMoveType() {
		return moveType;
	}

	/**
	 * @return the pieceType
	 */
	public HantoPieceType getPieceType() {
		return pieceType;
	}
}
