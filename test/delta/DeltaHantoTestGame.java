/**
 * 
 */
package delta;

import common.HantoTestGame;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoPieceImpl;
import hanto.studentdbtrue.delta.DeltaHantoGame;

/**
 * @author Dan
 *
 */
public class DeltaHantoTestGame extends DeltaHantoGame implements HantoTestGame {

	public DeltaHantoTestGame(HantoPlayerColor movesFirst) {
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
