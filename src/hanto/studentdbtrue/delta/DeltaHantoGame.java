/**
 * 
 */
package hanto.studentdbtrue.delta;

import java.util.ArrayList;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.HantoMovementType;
import hanto.studentdbtrue.common.Movement;
import hanto.studentdbtrue.common.rules.ButterflyWalksOneHex;
import hanto.studentdbtrue.common.rules.CantMakeMoveAfterGameIsOver;
import hanto.studentdbtrue.common.rules.CrabWalksOneHex;
import hanto.studentdbtrue.common.rules.MustBeContinuous;
import hanto.studentdbtrue.common.rules.NewPieceMustBeAdjacentToOwnColor;
import hanto.studentdbtrue.common.rules.WalkersCantMoveIfBlocked;

/**
 * @author Dan
 *
 */
public class DeltaHantoGame extends BaseHantoGame implements HantoGame {

	/**
	 * @param movesFirst
	 */
	public DeltaHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		setUpPlayers();
		setUpAdditionalRules();
		setUpPieceMovements();
	}
	
	private void setUpPlayers () {
		bluePlayer.addPiece(HantoPieceType.BUTTERFLY);
		redPlayer.addPiece(HantoPieceType.BUTTERFLY);
		
		for (int i = 0; i < 4; i++) {
			bluePlayer.addPiece(HantoPieceType.SPARROW);
			redPlayer.addPiece(HantoPieceType.SPARROW);
			
			bluePlayer.addPiece(HantoPieceType.CRAB);
			redPlayer.addPiece(HantoPieceType.CRAB);
		}
	}
	
	private void setUpAdditionalRules () {
		ruleSet.add(new MustBeContinuous());
		ruleSet.add(new NewPieceMustBeAdjacentToOwnColor());
		ruleSet.add(new CantMakeMoveAfterGameIsOver());
		ruleSet.add(new ButterflyWalksOneHex());
		ruleSet.add(new CrabWalksOneHex());
		ruleSet.add(new WalkersCantMoveIfBlocked());
	}
	
	private void setUpPieceMovements () {
		movementList = new ArrayList<Movement>();
		
		movementList.add(new Movement(HantoPieceType.BUTTERFLY, HantoMovementType.WALK, 1));
		movementList.add(new Movement(HantoPieceType.CRAB, HantoMovementType.WALK, 1));
		movementList.add(new Movement(HantoPieceType.SPARROW, HantoMovementType.FLY, -1));
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		MoveResult r;
		
		// If player resigns, do not run makeMove, but set gameOver to true, and set the moveResult to win
		if (pieceType == null && to == null && from == null) {
//			if (getAllValidMoves().size() != 0) {
//				throw new HantoPrematureResignationException();
//			}
			if (getCurrentPlayer().getColor() == HantoPlayerColor.RED) {
				r = MoveResult.BLUE_WINS;
			}
			else {
				r = MoveResult.RED_WINS;
			}
			
			gameOver = true;
		} 
		else {
			r = super.makeMove(pieceType, from, to);
		}
		
		return r;
	}
}
