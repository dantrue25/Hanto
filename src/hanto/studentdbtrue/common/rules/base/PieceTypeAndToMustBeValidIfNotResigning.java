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
public class PieceTypeAndToMustBeValidIfNotResigning extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		if ((to == null) != (p == null)) {
			throw new HantoException("Destination and piece type cannot be null, unless you are resigning.");
		}
		if (to == null && p == null && from != null) {
			throw new HantoException("Destination and piece type cannot be null, unless you are resigning.");
		}
		
	}

}
