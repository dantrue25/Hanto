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
import hanto.studentdbtrue.common.rules.GameRule;

/**
 * @author Dan
 *
 */
public class FirstMoveMustBeAtOrigin extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p, HantoCoordinate to,
			HantoCoordinate from) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		
		if (board.isEmpty() && !myTo.equals(new HantoCoordinateImpl(0, 0))) {
			throw new HantoException ("First move must be to (0, 0).");
		}
		
	}

}
