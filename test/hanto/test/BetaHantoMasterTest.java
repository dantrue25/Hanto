package hanto.test;

import hanto.*;
import hanto.common.*;
import hanto.studentdbtrue.beta.BetaHantoGame;

import org.junit.*;

import static org.junit.Assert.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;

public class BetaHantoMasterTest {
	
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;

		public TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}
	}

	private static HantoGameFactory factory;
	private HantoGame game;

	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoGameFactory.getInstance();
	}

	@Before
	public void setup()
	{
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO);
	}

	@Test
	public void getAnAlphaHantoGameFromTheFactory()
	{
		assertTrue(game instanceof BetaHantoGame);
	}

	@Test
	public void blueMakesValidFirstMove() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		assertEquals(OK, mr);
	}

	@Test
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, p.getType());
		assertEquals(BLUE, p.getColor());
	}

	@Test
	public void bluePlacesNonButterfly() throws HantoException
	{
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));
		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(SPARROW, p.getType());
		assertEquals(BLUE, p.getColor());
	}

	@Test
	public void redPlacesButterflyNextToBlueButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1));
		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 1));
		assertEquals(BUTTERFLY, p.getType());
		assertEquals(RED, p.getColor());
	}

	@Test(expected=HantoException.class)
	public void blueAttemptsToPlaceButterflyAtWrongLocation() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
	}

	@Test
	public void redMakesValidSecondMoveAndMoveIsOK() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
		assertEquals(MoveResult.OK, mr);
	}

	@Test(expected=HantoException.class)
	public void redPlacesButterflyNonAdjacentToBlueButterfly() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 2));
	}

	@Test(expected=HantoException.class)
	public void attemptToMoveRatherThanPlace() throws HantoException
	{
		game.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 1), new TestHantoCoordinate(0, 0));
	}
	
	@Test(expected=HantoException.class)
	public void attemptToNotPlayButterflyByFourthTurn() throws HantoException
	{
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Red
		game.makeMove(SPARROW, null, new TestHantoCoordinate(1, 1));   // Blue
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 2));   // Red
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 3));   // Blue
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 4));   // Red
		game.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 0));  // Blue
	}
	
	@Test(expected=HantoException.class)
	public void attemptToPlayTwoButterfly() throws HantoException
	{
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Red
		game.makeMove(SPARROW, null, new TestHantoCoordinate(1, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, 1)); // Red
	}
	
	@Test
	public void redWinsGame() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)); // Blue
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Red
		game.makeMove(SPARROW, null, new TestHantoCoordinate(1, 0));   // Blue
		game.makeMove(SPARROW, null, new TestHantoCoordinate(1, -1));  // Red
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, -1));  // Blue
		game.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 0));  // Red
		
		// Losing move: blue's butterfly is trapped
		final MoveResult mr = game.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 1));
		assertEquals(MoveResult.RED_WINS, mr);
	}

	@Test(expected=HantoException.class)
	public void attemptToPutPieceOnTopOfOther() throws HantoException
	{
		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)); // Red
	}
}
