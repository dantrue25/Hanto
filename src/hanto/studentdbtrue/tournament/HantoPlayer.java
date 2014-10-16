/**
 * 
 */
package hanto.studentdbtrue.tournament;

import java.util.List;

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.HantoGameFactory;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoMove;
import hanto.studentdbtrue.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * @author Dan
 *
 */
public class HantoPlayer implements HantoGamePlayer {

	private BaseHantoGame game;
	private HantoGameFactory factory;
	
	/**
	 * 
	 */
	@Override
	public void startGame(HantoGameID version, HantoPlayerColor myColor,
			boolean doIMoveFirst) {
		
		HantoPlayerColor movesFirst;
		
		if (myColor == HantoPlayerColor.BLUE) {
			if (doIMoveFirst) {
				movesFirst = HantoPlayerColor.BLUE;
			}
			else {
				movesFirst = HantoPlayerColor.RED;
			}
		}
		else {
			if (doIMoveFirst) {
				movesFirst = HantoPlayerColor.RED;
			}
			else {
				movesFirst = HantoPlayerColor.BLUE;
			}
		}
		
		factory = HantoGameFactory.getInstance();
		game = (EpsilonHantoGame) factory.makeHantoGame(version, movesFirst);

	}

	/**
	 * 
	 */
	@Override
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		HantoMoveRecord myMove = null;
		
		if (opponentsMove == null) {
			try {
				game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
				myMove = new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
			} 
			catch (HantoException e) {
				System.out.println("Move was invalid.");
			}
		}
		else {
			try {
				game.makeMove(opponentsMove.getPiece(), opponentsMove.getFrom(), opponentsMove.getTo());
			} 
			catch (HantoException e) {
				System.out.println("Opponents move was invalid.");
			}
			
			List<HantoMove> validMoves = game.getAllValidMoves();
			int moveSelection = (int) (Math.random() * validMoves.size());
			HantoMove validMove = validMoves.get(moveSelection);
			
			
			if (validMoves.size() == 0) {
				myMove = new HantoMoveRecord(null, null, null);
			}
			else {
				myMove = new HantoMoveRecord(validMove.getPieceType(), validMove.getFrom(), validMove.getTo());
			}
			
			try {
				game.makeMove(myMove.getPiece(), myMove.getFrom(), myMove.getTo());
			}
			catch (HantoException e) {
				System.out.println("Move was invalid.");
			}
		}
		
		return myMove;
	}

}
