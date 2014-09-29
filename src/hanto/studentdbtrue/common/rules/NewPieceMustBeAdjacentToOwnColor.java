/**
 * 
 */
package hanto.studentdbtrue.common.rules;

import java.util.List;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;
import hanto.studentdbtrue.common.HantoCoordinateImpl;

/**
 * @author Dan
 *
 */
public class NewPieceMustBeAdjacentToOwnColor extends GameRule {
	
	@Override
	public void check(BaseHantoGame game, Board board, HantoPieceType p,
			HantoCoordinate to, HantoCoordinate from) throws HantoException {
		
		HantoPlayerColor ownColor = game.getCurrentPlayer().getColor();
		List<HantoCoordinateImpl> occupiedCoords = board.adjacentPieceCoords(to);
		
		for (HantoCoordinate c : occupiedCoords) {
			if (board.getPieceAt(c).getColor() != ownColor && game.getTurnNum() != 1 && from == null) {
				throw new HantoException("Cannot place piece next to opposing color piece.");
			}
		}

	}

}
