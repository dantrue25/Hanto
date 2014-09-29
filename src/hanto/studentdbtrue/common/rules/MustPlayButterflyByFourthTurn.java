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
public class MustPlayButterflyByFourthTurn extends GameRule {

	@Override
	public void check (
			BaseHantoGame game,
			Board board,
			HantoPieceType pieceTypePlayed, 
			HantoCoordinate to, 
			HantoCoordinate from
			) throws HantoException {
		
		if (game.getTurnNum() == 4 
				&& game.getCurrentPlayer().hasPieceOfType(HantoPieceType.BUTTERFLY) 
				&& pieceTypePlayed != HantoPieceType.BUTTERFLY) {
			
			throw new HantoException ("Must play the butterfly by 4th turn.");
		}
		
	}

}
