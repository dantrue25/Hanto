/**
 * 
 */
package hanto.studentdbtrue.common.rules;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;

/**
 * @author Dan
 *
 */
public class MustBeContinuous extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		if (from != null && !board.isContinuous(from, to) && game.getTurnNum() != 1) {
			throw new HantoException("Has to be continuous.");
		}
		
	}

}
