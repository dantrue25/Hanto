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
public class FirstMoveMustBeAtOrigin extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p, HantoCoordinate to,
			HantoCoordinate from) throws HantoException {
		
		if (board.isEmpty() && !to.equals(new HantoCoordinateImpl(0, 0))) {
			throw new HantoException ("First move must be to (0, 0).");
		}
		
	}

}
