/**
 * 
 */
package gamma;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.HantoGameFactory;
import hanto.studentdbtrue.common.HantoCoordinateImpl;

import org.junit.Before;
import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGameFactory;

/**
 * @author Dan
 *
 */
public class GammaHantoMasterTest {
	private static HantoGameFactory factory = HantoGameFactory.getInstance();
	private static HantoTestGameFactory testFactory = HantoTestGameFactory.getInstance();
	private HantoGame game;
	private HantoTestGame testGame;

	/**
	 * Method setup.
	 */
	@Before
	public void setup()
	{
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO);
		testGame = testFactory.makeHantoTestGame(HantoGameID.GAMMA_HANTO);
	}
	
	/**
	 * Method makesBoardDiscontinuous.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void makesBoardDiscontinuous() throws HantoException
	{
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 2));   // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, -1));   // Blue
		
		game.makeMove(BUTTERFLY, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(-1, 2));   // Red
	}
	
	/**
	 * Method movesPieceStillContinuous.
	 * @throws HantoException
	 */
	@Test
	public void movesPieceStillContinuous() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 2));   // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, -1));   // Blue
		
		MoveResult r = game.makeMove(SPARROW, new HantoCoordinateImpl(0, 2), new HantoCoordinateImpl(1, 1));   // Red
		assertEquals(MoveResult.OK, r);	
	}
	
	/**
	 * Method attempsToMovePieceButBlocked.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attempsToMovePieceButBlocked() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(1, 1));   // Red
		game.makeMove(BUTTERFLY, new HantoCoordinateImpl(0, 0), new HantoCoordinateImpl(-1, 1));   // Blue
	}
	
	/**
	 * Method movePieceNotBlocked.
	 * @throws HantoException
	 */
	@Test
	public void movePieceNotBlocked() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(1, 1));   // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, -1));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 2));   // Red
		MoveResult r = game.makeMove(SPARROW, new HantoCoordinateImpl(0, -1), new HantoCoordinateImpl(1, -1));   // Blue
		assertEquals(MoveResult.OK, r);
	}
	
	/**
	 * Method movePieceMoreThanOneSpace.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void movePieceMoreThanOneSpace() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(1, 1));   // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, -1));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 2));   // Red
		game.makeMove(SPARROW, new HantoCoordinateImpl(0, -1), new HantoCoordinateImpl(-2, 0));   // Blue	
	}
	
	@Test
	public void makeMoveOn20thTurn() throws HantoException
	{
		HantoTestGame.PieceLocationPair[] plp = new HantoTestGame.PieceLocationPair[]{
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0, 0)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0, 1)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoCoordinateImpl(-1, 0)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoCoordinateImpl(0, -1)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoCoordinateImpl(1, 1)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoCoordinateImpl(0, 2))
		};
		testGame.initializeBoard(plp);
		testGame.setTurnNumber(20);
		
		MoveResult r1 = testGame.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, -1));
		MoveResult r2 = testGame.makeMove(SPARROW, null, new HantoCoordinateImpl(1, 2));
		assertEquals(MoveResult.OK, r1);
		assertEquals(MoveResult.DRAW, r2);
	}
	
	@Test
	public void makeMoveOn19thTurn() throws HantoException
	{
		HantoTestGame.PieceLocationPair[] plp = new HantoTestGame.PieceLocationPair[]{
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0, 0)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY, new HantoCoordinateImpl(0, 1)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoCoordinateImpl(-1, 0)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, new HantoCoordinateImpl(0, -1)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoCoordinateImpl(1, 1)),
				new HantoTestGame.PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, new HantoCoordinateImpl(0, 2))
		};
		testGame.initializeBoard(plp);
		testGame.setTurnNumber(19);
		
		MoveResult r1 = testGame.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, -1));
		MoveResult r2 = testGame.makeMove(SPARROW, null, new HantoCoordinateImpl(1, 2));
		
		assertEquals(MoveResult.OK, r1);
		assertEquals(MoveResult.OK, r2);
	}
	
	/**
	 * Method attemptToMakeMoveWithNullDestination.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMakeMoveWithNullDestination() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(1, 1));   // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, -1));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 2));   // Red
		game.makeMove(SPARROW, new HantoCoordinateImpl(0, -1), null);   // Blue	
	}
	
	/**
	 * Method attemptToMakeMoveNextToOpposing.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMakeMoveNextToOpposing() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(1, 0));   // Blue
	}
}
