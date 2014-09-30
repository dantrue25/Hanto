/**
 * 
 */
package hanto.studentdbtrue.common.rules;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoMovementType;

/**
 * @author Dan
 *
 */
public class WalkersCantMoveIfBlocked extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);
		
		if (from != null && game.getMovementType(p).getMoveType() == HantoMovementType.WALK) {

			List<HantoCoordinateImpl> sharedNeighbors = myTo.getSharedNeighbors(myFrom);
			HantoPiece[] piece = new HantoPiece[sharedNeighbors.size()];

			for (int i = 0; i < sharedNeighbors.size(); i++) {
				piece[i] = board.getPieceAt(sharedNeighbors.get(i));
			}

			boolean isBlocked = true;

			for (int j = 0; j < sharedNeighbors.size(); j++) {
				if (piece[j] == null) {
					isBlocked = false;
				}
			}

			if (isBlocked) {
				throw new HantoException("Piece is blocked that way.");
			}
		}
		
	}
	

}
