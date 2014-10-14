/**
 * 
 */
package hanto.studentdbtrue.common.rules;

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
public class JumpersJumpCorrectly extends GameRule {
	
	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		int deltaX, deltaY;
		boolean isValidJumpDirection;
		boolean isJumper = game.getMovementType(p).getMoveType() == HantoMovementType.JUMP;
		
		if (isJumper) {
			deltaX = to.getX() - from.getX();
			deltaY = to.getY() - from.getY();
			isValidJumpDirection = (deltaX == 0) != (deltaY == 0); // One (and only one) direction has to be 0
			
			if (!isValidJumpDirection)
				throw new HantoException("Jump movement is not correct.");
			else if (deltaY > 0) {
				for (int i = from.getY() + 1; i < to.getY() - 1; i++) {
					HantoPiece inbetweenPiece = board.getPieceAt(new HantoCoordinateImpl(from.getX(), i));
					if (inbetweenPiece == null) {
						throw new HantoException("Cannot jump over a gap.");
					}
				}
			}
			else if (deltaY < 0) {
				for (int i = to.getY() + 1; i < from.getY() - 1; i++) {
					HantoPiece inbetweenPiece = board.getPieceAt(new HantoCoordinateImpl(from.getX(), i));
					if (inbetweenPiece == null) {
						throw new HantoException("Cannot jump over a gap.");
					}
				}
			}
			else if (deltaX > 0) {
				for (int i = from.getX() + 1; i < to.getX() - 1; i++) {
					HantoPiece inbetweenPiece = board.getPieceAt(new HantoCoordinateImpl(from.getX(), i));
					if (inbetweenPiece == null) {
						throw new HantoException("Cannot jump over a gap.");
					}
				}
			}
			else if (deltaX < 0) {
				for (int i = to.getX() + 1; i < from.getX() - 1; i++) {
					HantoPiece inbetweenPiece = board.getPieceAt(new HantoCoordinateImpl(from.getX(), i));
					if (inbetweenPiece == null) {
						throw new HantoException("Cannot jump over a gap.");
					}
				}
			}
		}		
	}
}
