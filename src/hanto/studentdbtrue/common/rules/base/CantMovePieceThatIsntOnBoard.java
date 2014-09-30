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
public class CantMovePieceThatIsntOnBoard extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p, HantoCoordinate to,
			HantoCoordinate from) throws HantoException {
		
		if (board.isEmpty() && from != null) {
			throw new HantoException ("Cannot move a piece that is not on board.");
		}
		
	}

}
