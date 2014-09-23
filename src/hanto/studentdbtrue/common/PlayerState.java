/**
 * 
 */
package hanto.studentdbtrue.common;

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * @author Dan
 *
 */
public class PlayerState {
	
	private HantoPlayerColor color;
	private List<HantoPieceType> pieces;
	
	/**
	 * Constructor for PlayerState.
	 * @param color HantoPlayerColor
	 */
	public PlayerState (HantoPlayerColor color) {
		this.color = color;
		pieces = new ArrayList<HantoPieceType>();
	}
	
	/**
	 * Method hasPieceOfType.
	 * @param type HantoPieceType
	 * @return boolean
	 */
	public boolean hasPieceOfType (HantoPieceType type) {
		boolean hasPiece = false;
		
		for (HantoPieceType p : pieces) {
			if (p == type) {
				hasPiece = true;
				break;
			}
		}
		
		return hasPiece;
	}
	
	/**
	 * Method hasNoPieces.
	 * @return boolean
	 */
	public boolean hasNoPieces () {
		return pieces.isEmpty();
	}
	
	/**
	 * Method removePiece.
	 * @param type HantoPieceType
	 */
	public void removePiece (HantoPieceType type) {
		for (HantoPieceType p : pieces) {
			if (p == type) {
				pieces.remove(p);
				break;
			}
		}
	}
	
	/**
	 * Method addPiece.
	 * @param type HantoPieceType
	 */
	public void addPiece (HantoPieceType type) {
		pieces.add(type);
	}

	/**
	 * @return the color
	 */
	public HantoPlayerColor getColor() {
		return color;
	}
	
}
