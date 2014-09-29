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
public class SpaceMustNotAlreadyBeOccupied extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p, HantoCoordinate to,
			HantoCoordinate from) throws HantoException {
		
		if (board.getPieceAt(to) != null) {
			throw new HantoException ("A piece is already in that location.");
		}		
	}
}
