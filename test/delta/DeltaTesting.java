/**
 * 
 */
package delta;

import static org.junit.Assert.assertEquals;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentdbtrue.HantoGameFactory;
import hanto.studentdbtrue.common.HantoCoordinateImpl;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Dan
 *
 */
public class DeltaTesting {
	private static HantoGameFactory factory = HantoGameFactory.getInstance();
	private HantoGame game;

	/**
	 * Method setup.
	 */
	@Before
	public void setup()
	{
		game = factory.makeHantoGame(HantoGameID.DELTA_HANTO);
	}
	
	/**
	 * Method blueResignsAndResultIsRedWins.
	 * @throws HantoException
	 */
	@Test
	public void blueResignsAndResultIsRedWins() throws HantoException
	{
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(1, 0));
		game.makeMove(HantoPieceType.SPARROW, null, new HantoCoordinateImpl(-1, 1));
		game.makeMove(HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(1, 0), new HantoCoordinateImpl(0, 1));
		game.makeMove(HantoPieceType.SPARROW, new HantoCoordinateImpl(-1, 1), new HantoCoordinateImpl(0, 2));
		MoveResult r = game.makeMove(null, null, null);
		assertEquals(MoveResult.BLUE_WINS, r);
	}
	
	
	
	
	

}
