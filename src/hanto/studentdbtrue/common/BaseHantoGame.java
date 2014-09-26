/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentdbtrue.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dan
 *
 */
public abstract class BaseHantoGame implements HantoGame {

	protected Map<HantoCoordinate, HantoPiece> board = 
			new HashMap<HantoCoordinate, HantoPiece>();
	protected HantoPlayerColor movesFirst;
	protected HantoCoordinateImpl blueBLoc = null;
	protected HantoCoordinateImpl redBLoc = null;
	protected int turnNum = 1;
	protected PlayerState bluePlayer = new PlayerState(HantoPlayerColor.BLUE);
	protected PlayerState redPlayer = new PlayerState(HantoPlayerColor.RED);
	protected PlayerState currentPlayer;
	
	/**
	 * @throws HantoException 
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoPieceImpl p = new HantoPieceImpl(pieceType, currentPlayer.getColor());
		
		// Make sure player doesn't try to move a piece when there arent any
		if (board.isEmpty() && from != null) {
			throw new HantoException("Cannot move a piece that is not on the board.");
		}
		// Make sure first move is to (0, 0)
		if (board.isEmpty() && !myTo.equals(new HantoCoordinateImpl(0, 0))) {
			throw new HantoException ("First move must be to (0, 0).");
		}
		// Make sure move isn't to an occupied space
		if (board.get(to) != null) {
			throw new HantoException ("A piece is already in that location.");
		}
		// Make sure that player uses a butterfly by turn 4
		if (turnNum == 4 && currentPlayer.hasPieceOfType(HantoPieceType.BUTTERFLY) && pieceType != HantoPieceType.BUTTERFLY) {
			throw new HantoException ("Must play the butterfly by 4th turn.");
		}
		// Make sure new piece is adjacent to some piece on the board
		if (!isAdjacent(myTo) && !board.isEmpty()) {
			throw new HantoException ("Piece isn't adjacent to an existing piece.");
		}
		// Make sure player has the piece they are trying to place
		if (!currentPlayer.hasPieceOfType(pieceType)) {
			throw new HantoException ("Current player doesn't have any of those pieces.");
		}
		// If piece is butterfly, record the location
		if (pieceType == HantoPieceType.BUTTERFLY) {
			if(currentPlayer.getColor() == HantoPlayerColor.BLUE) {
				blueBLoc = myTo;
			}
			else {
				redBLoc = myTo;						
			}
		}
		
		// Put piece onto the board, and remove piece from player
		board.put(myTo, p);
		currentPlayer.removePiece(pieceType);
				
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
	
	/**
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		
		HantoCoordinateImpl myWhere = new HantoCoordinateImpl(where);
		
		return board.get(myWhere);
	}
	
	/**
	 * Method isAdjacent.
	 * @param loc HantoCoordinate
	 * @return boolean */
	protected boolean isAdjacent(HantoCoordinate loc) {
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
	
	 * @return boolean */
	protected boolean isSurrounded(HantoCoordinate loc) {
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
	
	/**
	 * Method switchPlayers.
	 */
	protected void switchPlayers () {
		if (currentPlayer == bluePlayer) {
			currentPlayer = redPlayer;
		}
		else {
			currentPlayer = bluePlayer;
		}
	}

}
