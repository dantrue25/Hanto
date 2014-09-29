/**
 * 
 */
package hanto.test;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dan
 *
 */
public class GammaHantoTest {
	private static HantoGameFactory factory = HantoGameFactory.getInstance();
	private HantoGame game;

	/**
	 * Method setup.
	 */
	@Before
	public void setup()
	{
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO);
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
		System.out.println("discontinuous");
		game.makeMove(SPARROW, new HantoCoordinateImpl(0, 1), new HantoCoordinateImpl(-1, 2));   // Red
	}
	
	/**
	 * Method movesPieceButContinuous.
	 * @throws HantoException
	 */
	@Test(expected=HantoException.class)
	public void movesPieceButContinuous() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 0));   // Blue
		game.makeMove(BUTTERFLY, null, new HantoCoordinateImpl(0, 1)); // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(-1, 0));   // Blue
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, 2));   // Red
		game.makeMove(SPARROW, null, new HantoCoordinateImpl(0, -1));   // Blue
		System.out.println("continuous");
		game.makeMove(SPARROW, new HantoCoordinateImpl(0, 2), new HantoCoordinateImpl(1, 1));   // Red
		game.makeMove(SPARROW, new HantoCoordinateImpl(0, 0), new HantoCoordinateImpl(1, -1));   // Blue
	}
}