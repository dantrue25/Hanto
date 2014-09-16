/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

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

/**
 */
public class BetaHantoGame implements HantoGame {

	private Map<HantoCoordinate, HantoPiece> board = 
			new HashMap<HantoCoordinate, HantoPiece>();
	
	private HantoPlayerColor currentTurn;
	private HantoPlayerColor movesFirst;
	
	private int numBlueSpar = 5;
	private int numRedSpar = 5;
	private int blueButterfly = 1;
	private int redButterfly = 1;
	
	private int turnNum = 1;
	private HantoCoordinateImpl blueBLoc = null;
	private HantoCoordinateImpl redBLoc = null;
	
	/**
	 * Constructor for BetaHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		this.movesFirst = movesFirst;
		currentTurn = movesFirst;
	}

	
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
			if( board.isEmpty() ) {                                   // If its the first move                                     
				if(myTo.getX() != 0 || myTo.getY() != 0) {            // If first move ISN'T to (0, 0)
					throw new HantoException( "Invalid coordinate" );
				}
				else if(pieceType == HantoPieceType.BUTTERFLY) {      // If first move IS to (0, 0) and it IS a butterfly
					board.put(myTo, p);
					blueBLoc = myTo;
					blueButterfly--;
				}
				else {                                                // If first move IS to (0, 0) and it IS NOT a butterfly 
					board.put(myTo, p);
					numBlueSpar--;
				}
			}
			else if(board.get(to) != null) {                          // If player is trying to move a piece rather than place it
				throw new HantoException("Piece already in that location.");
			}
			else if(turnNum == 4 && blueButterfly == 1) {             // If 4th turn and player hasn't used their butterfly
				if(pieceType != HantoPieceType.BUTTERFLY) {           // If 4th turn, player hasn't used their butterfly, and player ISN'T using butterfly
					throw new HantoException("Must use butterfly.");
				}
				else {                                                // If 4th turn, player hasn't used their butterfly, and player IS using butterfly
					if(isAdjacent(myTo)) {                            // If 4th turn, player hasn't used their butterfly, and player IS using butterfly, and it is valid
						board.put(myTo, p);
						blueBLoc = myTo;
						blueButterfly--;
					}
					else {                                            // If 4th turn, player hasn't used their butterfly, and player IS using butterfly, and it is invalid
						throw new HantoException("Isn't adjacent to another piece.");
					}
				}
			}
			else {                                                    // If not turn 4
				if(pieceType == HantoPieceType.BUTTERFLY) {           // If not turn 4 and piece IS butterfly
					if(blueButterfly == 1) {                          // If not turn 4, piece is butterfly, and player HASN'T used it already
						if(isAdjacent(myTo)) {                        // If not turn 4, piece is butterfly, player HASN'T used it, and move is valid
							board.put(myTo, p);
							blueBLoc = myTo;
							blueButterfly--;
						}
						else {                                        // If not turn 4, piece is butterfly, player HASN'T used it, and move is invalid
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
					else {                                            // If not turn 4, piece is butterfly, and player HAS used it already
						throw new HantoException("Already used butterfly.");
					}
				}
				else {                                                // If not turn 4 and piece is NOT butterfly
					if(numBlueSpar > 0) {                             // If not turn 4, piece is NOT butterfly, and the player has sparrows left
						if(isAdjacent(myTo)) {                        // If not turn 4, piece is NOT butterfly, the player has sparrows, and the move is valid
							board.put(myTo, p);
							numBlueSpar--;
						}
						else {                                        // If not turn 4, piece is NOT butterfly, the player has sparrows, and the move is invalid
							throw new HantoException("Isn't adjacent to another piece.");
						}
					}
					else {                                            // If not turn 4, piece is NOT butterfly, and the player DOESN'T have sparrows
						throw new HantoException("No more sparrows.");
					}
				}
			}
			break;
		case RED:
			if( board.isEmpty() ) {
				if(myTo.getX() != 0 || myTo.getY() != 0) {
					throw new HantoException( "Invalid coordinate" );
				}
				else if(pieceType == HantoPieceType.BUTTERFLY) {
					board.put(myTo, p);
					redBLoc = myTo;
					redButterfly--;
				}
				else {
					board.put(myTo, p);
					numRedSpar--;
				}
			}
			else if(turnNum == 4 && redButterfly == 1) {                                   
				if(pieceType != HantoPieceType.BUTTERFLY) {          
					throw new HantoException("Must use butterfly.");
				}
				else {                                               
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
			else {                                                      
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
		
		if(currentTurn != movesFirst) {
			turnNum++;
		}
		
		if(currentTurn == HantoPlayerColor.BLUE) {
			currentTurn = HantoPlayerColor.RED;
		}
		else {
			currentTurn = HantoPlayerColor.BLUE;
		}
		
		MoveResult result;
		
		if(isSurrounded(blueBLoc) && isSurrounded(redBLoc)) {
			result = MoveResult.DRAW;
		}
		else if(isSurrounded(blueBLoc)) {
			result = MoveResult.RED_WINS;
		}
		else if(isSurrounded(redBLoc)) {
			result = MoveResult.BLUE_WINS;
		}
		else if(numBlueSpar == 0 && numRedSpar == 0 && blueButterfly == 0 && redButterfly == 0) {
			result = MoveResult.DRAW;
		}
		else {
			result = MoveResult.OK;
		}
		
		return result;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		
		HantoCoordinateImpl myWhere = new HantoCoordinateImpl(where);
		
		return board.get(myWhere);
	}
	
	/**
	 * Method isAdjacent.
	 * @param loc HantoCoordinate
	 * @return boolean
	 */
	public boolean isAdjacent(HantoCoordinate loc) {
		HantoPiece adj1 = board.get(new HantoCoordinateImpl(loc.getX(), loc.getY() - 1));
		HantoPiece adj2 = board.get(new HantoCoordinateImpl(loc.getX(), loc.getY() + 1));
		HantoPiece adj3 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY()));
		HantoPiece adj4 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY()));
		HantoPiece adj5 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY() + 1));
		HantoPiece adj6 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY() - 1));
		
		// Check if there is an adjacent piece, and if not, return false
		return (!(adj1 == null && adj2 == null && adj3 == null && adj4 == null && adj5 == null && adj6 == null));
	}
	
	/**
	 * Method isSurrounded.
	 * @param loc HantoCoordinate
	 * @return boolean
	 */
	public boolean isSurrounded(HantoCoordinate loc) {
		if(loc == null) {
			return false;
		}
		
		HantoPiece adj1 = board.get(new HantoCoordinateImpl(loc.getX(), loc.getY() - 1));
		HantoPiece adj2 = board.get(new HantoCoordinateImpl(loc.getX(), loc.getY() + 1));
		HantoPiece adj3 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY()));
		HantoPiece adj4 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY()));
		HantoPiece adj5 = board.get(new HantoCoordinateImpl(loc.getX() - 1, loc.getY() + 1));
		HantoPiece adj6 = board.get(new HantoCoordinateImpl(loc.getX() + 1, loc.getY() - 1));
		
		// Check if there is an adjacent piece, and if not, return false
		return (!(adj1 == null || adj2 == null || adj3 == null || adj4 == null || adj5 == null || adj6 == null));
	}

	@Override
	public String getPrintableBoard() {
		String boardState = "";
		for (HantoCoordinate key: board.keySet()) {
		    HantoPiece p = board.get(key);
			boardState += p.getColor().toString() + " " + p.getType().toString() + " at (" + key.getX() + ", " + key.getY() + ")\n"; 
		}
		return boardState;
	}

}
