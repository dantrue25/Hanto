/**
 * 
 */
package epsilon;

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * @author Dan
 *
 */
public class GameDirector {
	HantoPlayer p1;
	HantoPlayer p2;
	int turnCount;
	
	public GameDirector () {
		p1 = new HantoPlayer();
		p2 = new HantoPlayer();
		
		p1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		p2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		
		turnCount = 0;
	}
	
	public void run () {
		turnCount++;
		HantoMoveRecord move = p1.makeMove(null);
		while (move.getPiece() != null && turnCount < 10) {
			turnCount++;
			move = p2.makeMove(move);
			if (move.getPiece() == null)
				break;
			move = p1.makeMove(move);
		}
	}
}
