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
public class SparrowsFlyMaxFourSpaces extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);
		
		if (p == HantoPieceType.SPARROW && myTo.distance(myFrom) > 4) {
			throw new HantoException("Sparrow cant fly more than 4 spaces.");
		}
		
	}

}
