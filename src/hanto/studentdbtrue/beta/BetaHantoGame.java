package hanto.studentdbtrue.beta;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoPieceImpl;

public class BetaHantoGame implements HantoGame {

	private Map<HantoCoordinate, HantoPiece> board = 
			new HashMap<HantoCoordinate, HantoPiece>();
	
	private HantoPlayerColor currentTurn = HantoPlayerColor.BLUE;
	
	private int numBlueSpar = 5;
	private int numRedSpar = 5;
	private int blueButterfly = 1;
	private int redButterfly = 1;
	
	private int turnNum = 1;
	private HantoCoordinateImpl blueBLoc = null;
	private HantoCoordinateImpl redBLoc = null;
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoPieceImpl p = new HantoPieceImpl(pieceType, currentTurn);
		
		if(from != null) {
			throw new HantoException("Cannot move a piece.");
		}
		
		switch(currentTurn) {
		case BLUE:
			if( board.isEmpty() ) {
				if(myTo.getX() != 0 || myTo.getY() != 0) {
					throw new HantoException( "Invalid coordinate" );
				}
				else if(pieceType == HantoPieceType.BUTTERFLY) {
					board.put(myTo, p);
					blueBLoc = myTo;
					blueButterfly--;
				}
				else {
					board.put(myTo, p);
					numBlueSpar--;
				}
			}
			else if(turnNum == 4) {                                           // If 4th turn
				if(blueButterfly == 1) {                                 // If player hasn't used butterfly
					if(pieceType != HantoPieceType.BUTTERFLY) {          // If player isn't using butterfly by turn 4
						throw new HantoException("Must use butterfly.");
					}
					else {                                               // If turn 4, but player is using butterfly
						if(isAdjacent(myTo)) {
							board.put(myTo, p);
							blueBLoc = myTo;
							blueButterfly--;
						}
						else {
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
				}
				else if(pieceType == HantoPieceType.BUTTERFLY) {         // If player is using butterfly, but already has
					throw new HantoException("Already used butterfly.");
				}
				else {                                                   // If player already used butterfly, and is now using a sparrow
					if(isAdjacent(myTo)) {                               // If new piece is adjacent to another, put it on board
						board.put(myTo, p);
						numBlueSpar--;
					}
					else {                                               // If new piece isn't adjacent, throw error
						throw new HantoException("Isn't adjacent to another piece.");
					}
				}
			}
			else {                                                       // If not turn 4
				if(pieceType == HantoPieceType.BUTTERFLY) {
					if(blueButterfly == 1) {
						if(isAdjacent(myTo)) {
							board.put(myTo, p);
							blueBLoc = myTo;
							blueButterfly--;
						}
						else {
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
					else {
						throw new HantoException("Already used butterfly.");
					}
				}
				else {
					if(numBlueSpar > 0) {
						if(isAdjacent(myTo)) {
							board.put(myTo, p);
							numBlueSpar--;
						}
						else {
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
					else {
						throw new HantoException("No more sparrows.");
					}
				}
			}
			break;
		case RED:
			if(turnNum == 4) {                                           // If 4th turn
				if(redButterfly == 1) {                                  // If player hasn't used butterfly
					if(pieceType != HantoPieceType.BUTTERFLY) {          // If player isn't using butterfly by turn 4
						throw new HantoException("Must use butterfly.");
					}
					else {                                               // If turn 4, but player is using butterfly
						if(isAdjacent(myTo)) {
							board.put(myTo, p);
							redBLoc = myTo;
							redButterfly--;
						}
						else {
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
				}
				else if(pieceType == HantoPieceType.BUTTERFLY) {         // If player is using butterfly, but already has
					throw new HantoException("Already used butterfly.");
				}
				else {                                                   // If player already used butterfly, and is now using a sparrow
					if(isAdjacent(myTo)) {                               // If new piece is adjacent to another, put it on board
						board.put(myTo, p);
						numRedSpar--;
					}
					else {                                               // If new piece isn't adjacent, throw error
						throw new HantoException("Isn't adjacent to another piece.");
					}
				}
			}
			else {                                                       // If not turn 4
				if(pieceType == HantoPieceType.BUTTERFLY) {
					if(redButterfly == 1) {
						if(isAdjacent(myTo)) {
							board.put(myTo, p);
							redBLoc = myTo;
							redButterfly--;
						}
						else {
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
					else {
						throw new HantoException("Already used butterfly.");
					}
				}
				else {
					if(numRedSpar > 0) {
						if(isAdjacent(myTo)) {
							board.put(myTo, p);
							numRedSpar--;
						}
						else {
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
					else {
						throw new HantoException("No more sparrows.");
					}
				}
			}
			break;
		}
		
		if(currentTurn == HantoPlayerColor.RED)
			turnNum++;
		
		if(currentTurn == HantoPlayerColor.BLUE)
			currentTurn = HantoPlayerColor.RED;
		else
			currentTurn = HantoPlayerColor.BLUE;
		
		
		if(isSurrounded(blueBLoc) && isSurrounded(redBLoc)) {
			return MoveResult.DRAW;
		}
		else if(isSurrounded(blueBLoc)) {
			return MoveResult.RED_WINS;
		}
		else if(isSurrounded(redBLoc)) {
			return MoveResult.BLUE_WINS;
		}
		
		if(numBlueSpar == 0 && numRedSpar == 0 && blueButterfly == 0 && redButterfly == 0) {
			return MoveResult.DRAW;
		}
		
		return MoveResult.OK;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		
		HantoCoordinateImpl myWhere = new HantoCoordinateImpl(where);
		
		return board.get(myWhere);
	}
	
	public boolean isAdjacent(HantoCoordinate loc) {
		HantoPiece adj1 = board.get(new HantoCoordinateImpl(loc.getX()    , loc.getY() - 1));
		HantoPiece adj2 = board.get(new HantoCoordinateImpl(loc.getX()    , loc.getY() + 1));
		HantoPiece adj3 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY()    ));
		HantoPiece adj4 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY()    ));
		HantoPiece adj5 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY() + 1));
		HantoPiece adj6 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY() - 1));
		
		// Check if there is an adjacent piece, and if not, return false
		return (!(adj1 == null && adj2 == null && adj3 == null && adj4 == null && adj5 == null && adj6 == null));
	}
	
	public boolean isSurrounded(HantoCoordinate loc) {
		if(loc == null) {
			return false;
		}
		
		HantoPiece adj1 = board.get(new HantoCoordinateImpl(loc.getX()    , loc.getY() - 1));
		HantoPiece adj2 = board.get(new HantoCoordinateImpl(loc.getX()    , loc.getY() + 1));
		HantoPiece adj3 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY()    ));
		HantoPiece adj4 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY()    ));
		HantoPiece adj5 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY() + 1));
		HantoPiece adj6 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY() - 1));
		
		// Check if there is an adjacent piece, and if not, return false
		return (!(adj1 == null || adj2 == null || adj3 == null || adj4 == null || adj5 == null || adj6 == null));
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
