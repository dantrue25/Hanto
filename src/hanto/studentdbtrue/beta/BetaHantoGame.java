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

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.*;

/**
 */
public class BetaHantoGame extends BaseHantoGame implements HantoGame {
	
	/**
	 * Constructor for BetaHantoGame.
	 * @param movesFirst HantoPlayerColor
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		this.movesFirst = movesFirst;
		
		if (movesFirst == HantoPlayerColor.BLUE) {
			currentPlayer = bluePlayer;
		}
		else {
			currentPlayer = redPlayer;
		}
		
		setUpPlayers();
	}
	
	private void setUpPlayers () {
		bluePlayer.addPiece(HantoPieceType.BUTTERFLY);
		redPlayer.addPiece(HantoPieceType.BUTTERFLY);
		
		for (int i = 0; i < 5; i++) {
			bluePlayer.addPiece(HantoPieceType.SPARROW);
			redPlayer.addPiece(HantoPieceType.SPARROW);
		}
	}

	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoPieceImpl p = new HantoPieceImpl(pieceType, currentPlayer.getColor());
		
		if(from != null) {
			throw new HantoException("Cannot move a piece.");
		}
		
		if( board.isEmpty() ) {                                   // If its the first move                                     
			if(myTo.getX() != 0 || myTo.getY() != 0) {            // If first move ISN'T to (0, 0)
				throw new HantoException( "Invalid coordinate" );
			}
			else if(pieceType == HantoPieceType.BUTTERFLY) {      // If first move IS to (0, 0) and it IS a butterfly
				board.put(myTo, p);

				if(currentPlayer.getColor() == HantoPlayerColor.BLUE) {
					blueBLoc = myTo;
				}
				else {
					redBLoc = myTo;						
				}

				currentPlayer.removePiece(pieceType);
			}
			else {                                                // If first move IS to (0, 0) and it IS NOT a butterfly 
				board.put(myTo, p);
				currentPlayer.removePiece(pieceType);
			}
		}
		else if(board.get(to) != null) {                          // If player is trying to move a piece rather than place it
			throw new HantoException("Piece already in that location.");
		}
		else if(turnNum == 4 && currentPlayer.hasPieceOfType(HantoPieceType.BUTTERFLY)) {             // If 4th turn and player hasn't used their butterfly
			if(pieceType != HantoPieceType.BUTTERFLY) {           // If 4th turn, player hasn't used their butterfly, and player ISN'T using butterfly
				throw new HantoException("Must use butterfly.");
			}
			else {                                                // If 4th turn, player hasn't used their butterfly, and player IS using butterfly
				if(isAdjacent(myTo)) {                            // If 4th turn, player hasn't used their butterfly, and player IS using butterfly, and it is valid
					board.put(myTo, p);

					if(currentPlayer.getColor() == HantoPlayerColor.BLUE) {
						blueBLoc = myTo;
					}
					else {
						redBLoc = myTo;						
					}

					currentPlayer.removePiece(pieceType);
				}
				else {                                            // If 4th turn, player hasn't used their butterfly, and player IS using butterfly, and it is invalid
					throw new HantoException("Isn't adjacent to another piece.");
				}
			}
		}
		else {                                                    // If not turn 4
			if(pieceType == HantoPieceType.BUTTERFLY) {           // If not turn 4 and piece IS butterfly
				if(currentPlayer.hasPieceOfType(HantoPieceType.BUTTERFLY)) {                          // If not turn 4, piece is butterfly, and player HASN'T used it already
					if(isAdjacent(myTo)) {                        // If not turn 4, piece is butterfly, player HASN'T used it, and move is valid
						board.put(myTo, p);

						if(currentPlayer.getColor() == HantoPlayerColor.BLUE) {
							blueBLoc = myTo;
						}
						else {
							redBLoc = myTo;						
						}

						currentPlayer.removePiece(pieceType);
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
				if(currentPlayer.hasPieceOfType(HantoPieceType.SPARROW)) {                             // If not turn 4, piece is NOT butterfly, and the player has sparrows left
					if(isAdjacent(myTo)) {                        // If not turn 4, piece is NOT butterfly, the player has sparrows, and the move is valid
						board.put(myTo, p);
						currentPlayer.removePiece(pieceType);
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
		
		if(currentPlayer.getColor() != movesFirst) {
			turnNum++;
		}
		
		switchPlayers();
		
		return getMoveResult();
	}
	
	private MoveResult getMoveResult () {
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
		else if(bluePlayer.hasNoPieces() && redPlayer.hasNoPieces()) {
			result = MoveResult.DRAW;
		}
		else {
			result = MoveResult.OK;
		}
		
		return result;
	}

}
