/**
 * 
 */
package common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.gamma.GammaHantoGame;

/**
 * @author Dan
 *
 */
public class GammaHantoTestGame implements HantoTestGame {

	private GammaHantoGame game;
	
	/**
	 * 
	 * @param movesFirst HantoPlayerColor
	 */
	public GammaHantoTestGame(HantoPlayerColor movesFirst) {
		game = new GammaHantoGame(movesFirst);
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return game.getPieceAt(where);
	}

	@Override
	public String getPrintableBoard() {
		return game.getPrintableBoard();
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		
		for (int i = 0; i < initialPieces.length; i++) {
			game.setCurrentPlayer(initialPieces[i].player);
			try {
				game.makeMove(initialPieces[i].pieceType, null, initialPieces[i].location);
			} catch (HantoException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void setTurnNumber(int turnNumber) {
		game.setTurnNum(turnNumber);

	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		game.setCurrentPlayer(player);
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		return game.makeMove(pieceType, from, to);
	}

}
