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
	 */
	public GammaHantoTestGame(HantoPlayerColor movesFirst) {
		game = new GammaHantoGame(movesFirst);
		game.setTurnNum(20);
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return game.getPieceAt(where);
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	@Override
	public String getPrintableBoard() {
		return game.getPrintableBoard();
	}

	/* (non-Javadoc)
	 * @see common.HantoTestGame#initializeBoard(common.HantoTestGame.PieceLocationPair[])
	 */
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

	/* (non-Javadoc)
	 * @see common.HantoTestGame#setTurnNumber(int)
	 */
	@Override
	public void setTurnNumber(int turnNumber) {
		game.setTurnNum(turnNumber);

	}

	/* (non-Javadoc)
	 * @see common.HantoTestGame#setPlayerMoving(hanto.common.HantoPlayerColor)
	 */
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
