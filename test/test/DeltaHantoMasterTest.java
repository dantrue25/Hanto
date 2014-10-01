/**
 * 
 */
package test;

import hanto.common.*;
import hanto.studentdbtrue.HantoGameFactory;
import hanto.studentdbtrue.common.HantoCoordinateImpl;

import org.junit.*;

import static org.junit.Assert.*;
import static hanto.common.HantoPieceType.*;

/**
 */

/**
 * @author Dan
 *
 */
public class DeltaHantoMasterTest {
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
		MoveResult r = game.makeMove(null, null, null);   // Blue
		assertEquals(MoveResult.RED_WINS, r);
	}
	
	/**
	 * Method blueResignsAndResultIsRedWins.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void blueResignsAndRedTriesToMove() throws HantoException
	{
		game.makeMove(null, null, null);   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
	}
	
	/**
	 * Method butterflyWalksOneHex.
	 * @throws HantoException
	 */
	@Test
	public void butterflyWalksOneHex() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1));
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));
		MoveResult r = game.makeMove(BUTTERFLY, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(1, 0));
		assertEquals(MoveResult.OK, r);
	}
	
	/**
	 * Method butterflyAttemptsToWalkTwoHex.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void butterflyAttemptsToWalkTwoHex() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1));
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));
		game.makeMove(BUTTERFLY, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(-2, 1));
	}
	
	/**
	 * Method crabWalksOneHex.
	 * @throws HantoException
	 */
	@Test
	public void crabWalksOneHex() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		game.makeMove(CRAB, null, new HantoCoordinateImpl(0, 1));
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));
		MoveResult r = game.makeMove(CRAB, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(1, 0));
		assertEquals(MoveResult.OK, r);
	}
	
	/**
	 * Method crabAttempsToWalkTwoHex.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void crabAttempsToWalkTwoHex() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		game.makeMove(CRAB, null, new HantoCoordinateImpl(0, 1));
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));
		game.makeMove(CRAB, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(-2, 1));
	}
	
	/**
	 * Method attemptToMoveIncorrectType.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveIncorrectType() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		game.makeMove(CRAB, null, new HantoCoordinateImpl(0, 1));
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));
		game.makeMove(BUTTERFLY, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(1, 0));
	}
	
	/**
	 * Method attemptToMoveWhenNothingIsThere.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveWhenNothingIsThere() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));
		game.makeMove(CRAB, null, new HantoCoordinateImpl(0, 1));
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));
		game.makeMove(BUTTERFLY, new HantoCoordinateImpl(1, 1), new HantoCoordinateImpl(1, 0));
	}
	
	/**
	 * Method sparrowFliesToAnotherLocation.
	 * @throws HantoException
	 */
	@Test
	public void sparrowFliesToAnotherLocation() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));     // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 1));       // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));      // Blue
		game.makeMove(SPARROW, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(-2, 1)); // Red
	}
	
	/**
	 * Method attemptToMoveANullPiece.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveANullPiece() throws HantoException
	{
		game.makeMove(null, null, new HantoCoordinateImpl(0, 0));
	}
	
	/**
	 * Method attemptToMoveToANullLocation.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveToANullLocation() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, null);
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
	 * Method attempsToMakeMoveWithToAndPieceNull.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attempsToMakeMoveWithToAndPieceNull() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(null, new HantoCoordinateImpl(0, 0), null);
	}
	
}
