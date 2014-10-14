/**
 * 
 */
package epsilon;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.HORSE;
import static hanto.common.HantoPieceType.CRAB;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.MoveResult;
import hanto.studentdbtrue.HantoGameFactory;
import hanto.studentdbtrue.common.HantoCoordinateImpl;

/**
 * @author Dan
 *
 */
public class EpsilonHantoMasterTest {
	
	private static HantoGameFactory factory = HantoGameFactory.getInstance();
	private HantoGame game;
	
	/**
	 * Method setup.
	 */
	@Before
	public void setup()
	{
		game = factory.makeHantoGame(HantoGameID.EPSILON_HANTO);
	}
	
	/**
	 * Method validSingleJumpWithAHorse.
	
	 * @throws HantoException */
	@Test
	public void validSingleJumpWithAHorse() throws HantoException
	{
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		MoveResult r = game.makeMove(HORSE, new HantoCoordinateImpl(0, 0), new HantoCoordinateImpl(0, 2));   // Blue
		
		assertEquals(MoveResult.OK, r);	
	}
	
	/**
	 * Method longJumpWithHorse.
	
	 * @throws HantoException */
	@Test
	public void longJumpWithHorse() throws HantoException
	{
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, -1));   // Blue
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, 2));   // Red
		
		MoveResult r = game.makeMove(HORSE, new HantoCoordinateImpl(0, -1), new HantoCoordinateImpl(0, 3));   // Blue
		assertEquals(MoveResult.OK, r);	
	}
	
	/**
	 * Method attemptWrongDirectionJumpWithAHorse.
	
	 * @throws HantoException */
	@Test(expected=HantoException.class)
	public void attemptWrongDirectionJumpWithAHorse() throws HantoException
	{
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		MoveResult r = game.makeMove(HORSE, new HantoCoordinateImpl(0, 0), new HantoCoordinateImpl(-1, 2));   // Blue
		
		assertEquals(MoveResult.OK, r);
	}
	
	/**
	 * Method attemptJumpOverHole.
	
	 * @throws HantoException */
	@Test(expected=HantoException.class)
	public void attemptJumpOverHole() throws HantoException
	{		
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(HORSE, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(-2, 1)); // Red
	}
	
	/**
	 * Method attemptToJumpHoleLong.
	
	 * @throws HantoException */
	@Test
	public void attemptToJumpHoleLong() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(CRAB, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(HORSE, null, new HantoCoordinateImpl(0, 2));   // Red
		game.makeMove(HORSE, null, new HantoCoordinateImpl(-2, 1)); // Blue
		game.makeMove(CRAB, null, new HantoCoordinateImpl(1, 1));   // Red
		game.makeMove(HORSE, new HantoCoordinateImpl(-2, 1), new HantoCoordinateImpl(0, -1));   // Blue
		MoveResult r = game.makeMove(HORSE, new HantoCoordinateImpl(0, 2), new HantoCoordinateImpl(0, -2));   // Red
		assertEquals(MoveResult.OK, r);	
	}
}
