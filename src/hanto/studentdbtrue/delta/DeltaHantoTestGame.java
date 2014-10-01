/**
 * 
 */
package hanto.studentdbtrue.delta;

import common.HantoTestGame;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoPieceImpl;

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
		
		for (int i = 0; i < initialPieces.length; i++) {
			HantoPieceImpl newPiece = new HantoPieceImpl(initialPieces[i].pieceType, initialPieces[i].player);
			board.putPieceOn(new HantoCoordinateImpl(initialPieces[i].location), newPiece);
			setCurrentPlayer(initialPieces[i].player);
			currentPlayer.removePiece(initialPieces[i].pieceType);
		}
		
		turnNum = (initialPieces.length / 2);
		setCurrentPlayer(movesFirst);
		
		if (initialPieces.length % 2 == 1) {
			switchPlayers();
		}

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
