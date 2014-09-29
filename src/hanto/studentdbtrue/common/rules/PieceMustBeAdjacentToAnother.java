/**
 * 
 */
package hanto.studentdbtrue.common.rules;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;
import hanto.studentdbtrue.common.HantoCoordinateImpl;

/**
 * @author Dan
 *
 */
public class PieceMustBeAdjacentToAnother extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p, HantoCoordinate to,
			HantoCoordinate from) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl (to);
		
		if (!board.isDestinationAdjacentToAnother(myTo) && !board.isEmpty()) {
			throw new HantoException ("Piece isn't adjacent to an existing piece.");
		}		
	}
}
