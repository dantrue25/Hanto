/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.test;

import hanto.*;
import hanto.common.*;
import hanto.studentdbtrue.beta.BetaHantoGame;
import hanto.studentdbtrue.common.HantoCoordinateImpl;

import org.junit.*;

import static org.junit.Assert.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;

/**
 */
public class BetaHantoMasterTest {
	
	/**
	 */
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;

		/**
		 * Constructor for TestHantoCoordinate.
		 * @param x int
		 * @param y int
		 */
		private TestHantoCoordinate(int x, int y)
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

	private static HantoGameFactory factory = HantoGameFactory.getInstance();
	private HantoGame gameBlueFirst;
	private HantoGame gameRedFirst;

	/**
	 * Method setup.
	 */
	@Before
	public void setup()
	{
		gameBlueFirst = factory.makeHantoGame(HantoGameID.BETA_HANTO);
		gameRedFirst = factory.makeHantoGame(HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
	}

	/**
	 * Method getABetaHantoGameFromTheFactory.
	 */
	@Test
	public void getABetaHantoGameFromTheFactory()
	{
		assertTrue(gameBlueFirst instanceof BetaHantoGame);
	}

	/**
	 * Method blueMakesValidFirstMoveButterfly.
	 * @throws HantoException
	 */
	@Test
	public void blueMakesValidFirstMoveButterfly() throws HantoException
	{
		final MoveResult mr = gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		assertEquals(OK, mr);
	}
	
	/**
	 * Method redMakesValidFirstMoveButterfly.
	 * @throws HantoException
	 */
	@Test
	public void redMakesValidFirstMoveButterfly() throws HantoException
	{
		final MoveResult mr = gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		assertEquals(OK, mr);
	}
	
	/**
	 * Method blueMakesValidFirstMoveSparrow.
	 * @throws HantoException
	 */
	@Test
	public void blueMakesValidFirstMoveSparrow() throws HantoException
	{
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));
		String gameState = gameBlueFirst.getPrintableBoard();
		assertEquals("BLUE Sparrow at (0, 0)\n", gameState);
	}
	
	/**
	 * Method redMakesValidFirstMoveSparrow.
	 * @throws HantoException
	 */
	@Test
	public void redMakesValidFirstMoveSparrow() throws HantoException
	{
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));
		String gameState = gameRedFirst.getPrintableBoard();
		assertEquals("RED Sparrow at (0, 0)\n", gameState);
	}

	/**
	 * Method afterFirstMoveBlueButterflyIsAt0_0.
	 * @throws HantoException
	 */
	@Test
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException
	{
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final HantoPiece p = gameBlueFirst.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, p.getType());
		assertEquals(BLUE, p.getColor());
	}
	
	/**
	 * Method afterFirstMoveRedButterflyIsAt0_0.
	 * @throws HantoException
	 */
	@Test
	public void afterFirstMoveRedButterflyIsAt0_0() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final HantoPiece p = gameRedFirst.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, p.getType());
		assertEquals(RED, p.getColor());
	}
	
	/**
	 * Method blueAttemptsToPlaceButterflyAtWrongLocation.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void blueAttemptsToPlaceButterflyAtWrongLocation() throws HantoException
	{
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
	}
	
	/**
	 * Method redAttemptsToPlaceButterflyAtWrongLocation.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void redAttemptsToPlaceButterflyAtWrongLocation() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
	}
	
	/**
	 * Method redMakesValidSecondMoveButterfly.
	 * @throws HantoException
	 */
	@Test
	public void redMakesValidSecondMoveButterfly() throws HantoException
	{
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final MoveResult mr = gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
		assertEquals(MoveResult.OK, mr);
	}
	
	/**
	 * Method blueMakesValidSecondMoveButterfly.
	 * @throws HantoException
	 */
	@Test
	public void blueMakesValidSecondMoveButterfly() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final MoveResult mr = gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
		assertEquals(MoveResult.OK, mr);
	}
	
	/**
	 * Method redMakesInvalidSecondMoveButterfly.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void redMakesInvalidSecondMoveButterfly() throws HantoException
	{
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 2));
	}
	
	/**
	 * Method blueMakesInvalidSecondMoveButterfly.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void blueMakesInvalidSecondMoveButterfly() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 2));
	}
	
	/**
	 * Method attemptToMoveRatherThanPlaceBlue.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveRatherThanPlaceBlue() throws HantoException
	{
		gameBlueFirst.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 1), new TestHantoCoordinate(0, 0));
	}
	
	/**
	 * Method attemptToMoveRatherThanPlaceRed.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToMoveRatherThanPlaceRed() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 1), new TestHantoCoordinate(0, 0));
	}
	
	/**
	 * Method attemptToNotPlayButterflyByFourthTurnBlue.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToNotPlayButterflyByFourthTurnBlue() throws HantoException
	{
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Blue
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 1));   // Blue
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 2));   // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 3));   // Blue
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 4));   // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 0));  // Blue
	}
	
	/**
	 * Method attemptToNotPlayButterflyByFourthTurnRed.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToNotPlayButterflyByFourthTurnRed() throws HantoException
	{
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); 
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 1));  
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 2));  
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 3));  
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 4));  
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 0)); 
	}
	
	/**
	 * Method playButterflyOnFourthTurnBlue.
	 * @throws HantoException
	 */
	@Test
	public void playButterflyOnFourthTurnBlue() throws HantoException
	{
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Blue
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 1));   // Blue
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 2));   // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 3));   // Blue
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 4));   // Red
		
		MoveResult mr = gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 0));
		assertEquals(OK, mr);
	}
	
	/**
	 * Method playButterflyOnFourthTurnRed.
	 * @throws HantoException
	 */
	@Test
	public void playButterflyOnFourthTurnRed() throws HantoException
	{
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); 
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 1));  
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 2));  
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 3));  
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 4));
		
		MoveResult mr = gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 0));
		assertEquals(OK, mr);
	}
	
	/**
	 * Method attemptToPlayTwoButterflyBlue.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToPlayTwoButterflyBlue() throws HantoException
	{
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Blue
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 0));   // Blue
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, 1)); // Red
	}
	
	/**
	 * Method attemptToPlayTwoButterflyRed.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToPlayTwoButterflyRed() throws HantoException
	{
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Red
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 0));   // Red
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(1, 1)); // Blue
	}
	
	/**
	 * Method redWinsGame.
	 * @throws HantoException
	 */
	@Test
	public void redWinsGame() throws HantoException
	{
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)); // Blue
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 0));   // Blue
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, -1));  // Red
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, -1));  // Blue
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 0));  // Red
		
		// Losing move: blue's butterfly is trapped
		final MoveResult mr = gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 1));
		
		assertEquals(MoveResult.RED_WINS, mr);
	}
	
	/**
	 * Method blueWinsGame.
	 * @throws HantoException
	 */
	@Test
	public void blueWinsGame() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)); // Red
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 0));   // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, -1));  // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, -1));  // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 0));  // Blue
		
		// Losing move: blue's butterfly is trapped
		final MoveResult mr = gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 1));
		
		assertEquals(MoveResult.BLUE_WINS, mr);
	}

	/**
	 * Method attemptToPutPieceOnTopOfOtherBlue.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToPutPieceOnTopOfOtherBlue() throws HantoException
	{
		gameBlueFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Blue
		gameBlueFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)); // Red
	}
	
	/**
	 * Method attemptToPutPieceOnTopOfOtherRed.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void attemptToPutPieceOnTopOfOtherRed() throws HantoException
	{
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));   // Red
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)); // Blue
	}
	
	/**
	 * Method useAllPieces.
	 * @throws HantoException
	 */
	@Test
	public void useAllPieces() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0)); // Red
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1)); // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, 0));   // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(1, -1));  // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, -1));  // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 0));  // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 2));  // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 3));  // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 4));  // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 5));  // Blue
		
		final MoveResult mrOk = gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 6));
		final MoveResult mrDraw = gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 7));
		assertEquals(OK, mrOk);
		assertEquals(DRAW, mrDraw);
		
	}
	
	/**
	 * Method bothLose.
	 * @throws HantoException
	 */
	@Test
	public void bothLose() throws HantoException
	{
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate( 0,  0)); // Red
		gameRedFirst.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1,  0)); // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate  (-2,  1)); // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate  (-2,  0)); // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate  (-1, -1)); // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate  ( 0, -1)); // Blue
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate  ( 1, -1)); // Red
		gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate  ( 1,  0)); // Blue
		
		final MoveResult mrOk = gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(0, 1));
		final MoveResult mrDraw = gameRedFirst.makeMove(SPARROW, null, new TestHantoCoordinate(-1, 1));
		assertEquals(OK, mrOk);
		assertEquals(DRAW, mrDraw);
		
	}
	
	/**
	 * Method testTheEqualsFunctionInHantoCoordinateImpl.
	 */
	@Test
	public void testTheEqualsFunctionInHantoCoordinateImpl()
	{
		HantoCoordinateImpl first = new HantoCoordinateImpl(0, 0);
		HantoCoordinateImpl diffInY = new HantoCoordinateImpl(0, 1);
		HantoCoordinateImpl diffInX = new HantoCoordinateImpl(1, 0);
		HantoCoordinateImpl equalToFirst = new HantoCoordinateImpl(0, 0);
		String otherType = "";
		
		assertFalse(first.equals(null));
		assertTrue(first.equals(first));
		assertTrue(first.equals(equalToFirst));
		assertFalse(first.equals(diffInX));
		assertFalse(first.equals(diffInY));
		assertFalse(first.equals(otherType));
	}
}
