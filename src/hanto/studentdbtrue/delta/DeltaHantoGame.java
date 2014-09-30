/**
 * 
 */
package hanto.studentdbtrue.delta;

import java.util.ArrayList;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentdbtrue.common.BaseHantoGame;
import hanto.studentdbtrue.common.HantoMovementType;
import hanto.studentdbtrue.common.Movement;
import hanto.studentdbtrue.common.rules.ButterflyWalksOneHex;
import hanto.studentdbtrue.common.rules.CantMakeMoveAfterGameIsOver;
import hanto.studentdbtrue.common.rules.CrabWalksOneHex;
import hanto.studentdbtrue.common.rules.MustBeContinuous;
import hanto.studentdbtrue.common.rules.NewPieceMustBeAdjacentToOwnColor;
import hanto.studentdbtrue.common.rules.ResignIfMakeMoveNullNullNull;
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
		ruleSet.add(new ResignIfMakeMoveNullNullNull());
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
}
