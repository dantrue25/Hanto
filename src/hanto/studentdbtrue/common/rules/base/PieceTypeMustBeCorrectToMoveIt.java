/**
 * 
 */
package hanto.studentdbtrue.common.rules.base;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;
import hanto.studentdbtrue.common.rules.GameRule;

/**
 * @author Dan
 *
 */
public class PieceTypeMustBeCorrectToMoveIt extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		if (from != null && board.getPieceAt(from) == null) {
			throw new HantoException("There is no piece at this location, or it is not the right type.");
		}
		else if (from != null && board.getPieceAt(from).getType() != p) {
			throw new HantoException("There is no piece at this location, or it is not the right type.");
		}
		
	}

}
