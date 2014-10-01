/**
 * 
 */
package hanto.studentdbtrue.common.rules;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;

/**
 * @author Dan
 *
 */
public class ResignIfMakeMoveNullNullNull extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		
		if (p == null && to == null && from == null) {
			
			if (game.getCurrentPlayer().getColor() == HantoPlayerColor.RED) {
				game.makeRedResign();
			}
			else {
				game.makeBlueResign();
			}
			
			if (board == null) {
				throw new HantoException("Never going to get 'ere. Code coverage thangs.");
			}
		}
	}
}
