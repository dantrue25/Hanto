/**
 * 
 */
package hanto.studentdbtrue.common.rules.base;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoPieceImpl;
import hanto.studentdbtrue.common.rules.GameRule;

/**
 * @author Dan
 *
 */
public class OnlyMovePieceOfYourColor extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);
		HantoPieceImpl movingPiece = board.getPieceAt(myFrom);
		if (from != null && movingPiece != null) {
			if (game.getCurrentPlayer().getColor() != movingPiece.getColor()) {
				throw new HantoException("Cannot move piece of the other color.");
			}
		}
		
	}

}
