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

/**
 * @author Dan
 *
 */
public class CantMoveIfBlocked extends GameRule {

	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		if (from != null) {
			HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
			HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);

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
