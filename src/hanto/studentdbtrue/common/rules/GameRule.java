/**
 * 
 */
package hanto.studentdbtrue.common.rules;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.Board;

/**
 * @author Dan
 *
 */
public abstract class GameRule {
	
	/**
	 * Method check.
	 * @param game BaseHantoGame
	 * @param board Board
	 * @param p HantoPieceType
	 * @param to HantoCoordinate
	 * @param from HantoCoordinate
	 * @throws HantoException
	 */
	public abstract void check (
			BaseHantoGame game,
			Board board, 
			HantoPieceType p, 
			HantoCoordinate to, 
			HantoCoordinate from ) throws HantoException;

}
