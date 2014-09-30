/**
 * 
 */
package hanto.studentdbtrue.common.rules;

import java.util.List;

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
public class ButterflyWalksOneHex extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		
		if (from != null && p == HantoPieceType.BUTTERFLY) {
			List<HantoCoordinateImpl> fromNeighbors = myFrom.getNeighbors();
			if (!fromNeighbors.contains(myTo)) {
				throw new HantoException ("The Butterfly can only walk one space.");
			}
			
		}
		
	}

}
