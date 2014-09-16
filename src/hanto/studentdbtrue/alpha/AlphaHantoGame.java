/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentdbtrue.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentdbtrue.common.HantoCoordinateImpl;
import hanto.studentdbtrue.common.HantoPieceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class AlphaHantoGame implements HantoGame {

	private Map<HantoCoordinate, HantoPiece> board = 
			new HashMap<HantoCoordinate, HantoPiece>();
	
	private HantoPlayerColor currentTurn = HantoPlayerColor.BLUE;
	
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		MoveResult result = null;
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		
		if( pieceType != HantoPieceType.BUTTERFLY ) {
			throw new HantoException( "Invalid piece" );
		}
		if( from != null ) {
			throw new HantoException( "Cannot move piece" );
		}
		
		// If the board is empty, make sure first move is to (0,0)
		if( board.isEmpty() ) {
			if(myTo.getX() != 0 || myTo.getY() != 0) {
				throw new HantoException( "Invalid coordinate" );
			}
			
			result = MoveResult.OK;
		}
		else {
			// Check if there is an adjacent piece, and if not, throw exception
			if(isAdjacent(myTo)) {
				result = MoveResult.DRAW;
			}
			else {
				throw new HantoException("Invalid Move");
			}
		}
		
		HantoPiece p = new HantoPieceImpl( pieceType, currentTurn );
		
		if( currentTurn == HantoPlayerColor.BLUE) {
			currentTurn = HantoPlayerColor.RED;
		}
		else {
			currentTurn = HantoPlayerColor.BLUE;
		}
		
		board.put(myTo, p);
		
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
