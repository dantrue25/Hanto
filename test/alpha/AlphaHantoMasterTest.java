/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package alpha;

import hanto.common.*;
import hanto.studentdbtrue.HantoGameFactory;
import hanto.studentdbtrue.alpha.*;

import org.junit.*;

import static org.junit.Assert.*;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import static hanto.common.MoveResult.*;

/**
 */
public class AlphaHantoMasterTest {
		/**
		 * Internal class for these test cases.
		 * @version Sep 13, 2014
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
		private HantoGame game;

		/**
		 * Method setup.
		 */
		@Before
		public void setup()
		{
			game = factory.makeHantoGame(HantoGameID.ALPHA_HANTO);
		}

		/**
		 * Method getAnAlphaHantoGameFromTheFactory.
		 */
		@Test
		public void getAnAlphaHantoGameFromTheFactory()
		{
			assertTrue(game instanceof AlphaHantoGame);
		}

		/**
		 * Method blueMakesValidFirstMove.
		 * @throws HantoException
		 */
		@Test
		public void blueMakesValidFirstMove() throws HantoException
		{
			final MoveResult mr = game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
			String boardState = game.getPrintableBoard();
			
			assertEquals(OK, mr);
			assertEquals("BLUE Butterfly at (0, 0)\n", boardState);
		}

		/**
		 * Method afterFirstMoveBlueButterflyIsAt0_0.
		 * @throws HantoException
		 */
		@Test
		public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException
		{
			game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
			final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 0));
			assertEquals(BUTTERFLY, p.getType());
			assertEquals(BLUE, p.getColor());
		}

		/**
		 * Method bluePlacesNonButterfly.
		 * @throws HantoException
		 */
		@Test(expected=HantoException.class)
		public void bluePlacesNonButterfly() throws HantoException
		{
			game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));
		}

		/**
		 * Method redPlacesButterflyNextToBlueButterfly.
		 * @throws HantoException
		 */
		@Test
		public void redPlacesButterflyNextToBlueButterfly() throws HantoException
		{
			game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
			game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1));
			final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 1));
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
			game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
		}

		/**
		 * Method redMakesValidSecondMoveAndGameIsDrawn.
		 * @throws HantoException
		 */
		@Test
		public void redMakesValidSecondMoveAndGameIsDrawn() throws HantoException
		{
			game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
			final MoveResult mr = game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
			assertEquals(MoveResult.DRAW, mr);
		}

		/**
		 * Method redPlacesButterflyNonAdjacentToBlueButterfly.
		 * @throws HantoException
		 */
		@Test(expected=HantoException.class)
		public void redPlacesButterflyNonAdjacentToBlueButterfly() throws HantoException
		{
			game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
			game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 2));
		}

		/**
		 * Method attemptToMoveRatherThanPlace.
		 * @throws HantoException
		 */
		@Test(expected=HantoException.class)
		public void attemptToMoveRatherThanPlace() throws HantoException
		{
			game.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 1), new TestHantoCoordinate(0, 0));
		}
	}
