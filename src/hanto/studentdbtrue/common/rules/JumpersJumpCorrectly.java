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
import hanto.studentdbtrue.common.HantoMovementType;

/**
 * @author Dan
 *
 */
public class JumpersJumpCorrectly extends GameRule {
	
	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);
		
		int deltaX, deltaY;
		boolean isValidJumpDirection;
		boolean isJumper = game.getMovementType(p).getMoveType() == HantoMovementType.JUMP;
		
		if (isJumper && from != null) {
			deltaX = to.getX() - from.getX();
			deltaY = to.getY() - from.getY();
			
			boolean isDiagonal = (deltaY == -1 * deltaX);
			boolean isSingleDirection = ((deltaX == 0) != (deltaY == 0));
			int diagonalDist = Math.abs(deltaY);
			int singleDirDist = Math.abs(deltaX) + Math.abs(deltaY);
			int distance = 0;
			
			if (isDiagonal) {
				distance = diagonalDist;
			}
			else if (isSingleDirection) {
				distance = singleDirDist;
			}
			
			isValidJumpDirection = (isDiagonal || isSingleDirection) && distance > 1; // One (and only one) direction has to be 0
			
			if (!isValidJumpDirection) {
				throw new HantoException("Jump direction is not correct.");
			}
			else {
				List<HantoCoordinateImpl> inbetween = myTo.getCoordsInbetween(myFrom);
				for (HantoCoordinateImpl c : inbetween) {
					if (board.getPieceAt(c) == null) {
						throw new HantoException("Cannot jump over a hole.");
					}
				}
			}
		}		
	}
}
