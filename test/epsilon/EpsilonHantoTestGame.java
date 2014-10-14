/**
 * 
 */
package epsilon;

import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoPieceImpl;
import hanto.studentdbtrue.epsilon.EpsilonHantoGame;
import common.HantoTestGame;

/**
 * @author Dan
 *
 */
public class EpsilonHantoTestGame extends EpsilonHantoGame implements
		HantoTestGame {

	public EpsilonHantoTestGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		board.clear();
		for (int i = 0; i < initialPieces.length; i++) {
			HantoPieceImpl newPiece = new HantoPieceImpl(initialPieces[i].pieceType, initialPieces[i].player);
			board.putPieceOn(new HantoCoordinateImpl(initialPieces[i].location), newPiece);
			setCurrentPlayer(initialPieces[i].player);
			currentPlayer.removePiece(initialPieces[i].pieceType);
		}
		
		turnNum = (initialPieces.length / 2);
		
		setCurrentPlayer(movesFirst);

	}

	@Override
	public void setTurnNumber(int turnNumber) {
		turnNum = turnNumber;

	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		setCurrentPlayer(player);

	}

}
