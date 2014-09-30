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
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the type
	 */
	public HantoMovementType getMoveType() {
		return moveType;
	}

	/**
	 * @param type the type to set
	 */
	public void setMoveType(HantoMovementType moveType) {
		this.moveType = moveType;
	}

	/**
	 * @return the pieceType
	 */
	public HantoPieceType getPieceType() {
		return pieceType;
	}

	/**
	 * @param pieceType the pieceType to set
	 */
	public void setPieceType(HantoPieceType pieceType) {
		this.pieceType = pieceType;
	}
}
