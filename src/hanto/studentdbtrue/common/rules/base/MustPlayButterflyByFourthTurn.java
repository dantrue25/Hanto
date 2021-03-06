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
public class MustPlayButterflyByFourthTurn extends GameRule {

	@Override
	public void check (
			BaseHantoGame game,
			Board board,
			HantoPieceType pieceTypePlayed, 
			HantoCoordinate to, 
			HantoCoordinate from
			) throws HantoException {
		
		if (game.getTurnNum() == 3 
				&& game.getCurrentPlayer().hasPieceOfType(HantoPieceType.BUTTERFLY) 
				&& pieceTypePlayed != HantoPieceType.BUTTERFLY) {
			
			throw new HantoException ("Must play the butterfly by 4th turn.");
		}
		
	}

}
