/**
 * 
 */
package hanto.studentdbtrue.epsilon;

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
import hanto.studentdbtrue.common.rules.JumpersJumpCorrectly;
import hanto.studentdbtrue.common.rules.MustBeContinuous;
import hanto.studentdbtrue.common.rules.NewPieceMustBeAdjacentToOwnColor;
import hanto.studentdbtrue.common.rules.WalkersCantMoveIfBlocked;

/**
 * @author Dan
 *
 */
public class EpsilonHantoGame extends BaseHantoGame implements HantoGame {

	/**
	 * @param movesFirst
	 */
	public EpsilonHantoGame(HantoPlayerColor movesFirst) {
		super(movesFirst);
		setUpPlayers();
		setUpAdditionalRules();
		setUpPieceMovements();
	}
	
	private void setUpPlayers () {
		bluePlayer.addPiece(HantoPieceType.BUTTERFLY);
		redPlayer.addPiece(HantoPieceType.BUTTERFLY);
		
		for (int i = 0; i < 6; i++) {
			if (i < 4) {
				if (i < 2) {
					bluePlayer.addPiece(HantoPieceType.SPARROW);
					redPlayer.addPiece(HantoPieceType.SPARROW);
				}
				bluePlayer.addPiece(HantoPieceType.HORSE);
				redPlayer.addPiece(HantoPieceType.HORSE);
			}
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
		ruleSet.add(new JumpersJumpCorrectly());
	}
	
	private void setUpPieceMovements () {
		movementList = new ArrayList<Movement>();
		
		movementList.add(new Movement(HantoPieceType.BUTTERFLY, HantoMovementType.WALK, 1));
		movementList.add(new Movement(HantoPieceType.CRAB, HantoMovementType.WALK, 1));
		movementList.add(new Movement(HantoPieceType.SPARROW, HantoMovementType.FLY, 4));
		movementList.add(new Movement(HantoPieceType.HORSE, HantoMovementType.JUMP, -1));
	}
	
}
