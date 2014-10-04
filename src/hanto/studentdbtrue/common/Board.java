/**
 * 
 */
package hanto.studentdbtrue.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Dan
 *
 */
public class Board {
	
	private Map<HantoCoordinate, HantoPieceImpl> board;
	protected HantoCoordinateImpl blueBLoc = null;
	protected HantoCoordinateImpl redBLoc = null;
	
	/**
	 * Constructor for Board.
	 */
	public Board () {
		board = new HashMap<HantoCoordinate, HantoPieceImpl>();
	}
	
	/**
	 * @return true if board is empty
	 */
	public boolean isEmpty () {
		return board.isEmpty();
	}
	
	/**
	 * @param where HantoCoordinate
	
	 * @return HantoPiece  */
	public HantoPieceImpl getPieceAt(HantoCoordinate where) {
		HantoPieceImpl p = null;
		HantoCoordinateImpl myWhere = new HantoCoordinateImpl(where);
		
		if (where != null) {
			p = board.get(myWhere);
		}
		
		return p;
	}
	
	/**
	 * @param c HantoCoordinateImpl
	 * @param p HantoPieceImpl
	 */
	public void putPieceOn (HantoCoordinateImpl c, HantoPieceImpl p) {
		board.put(c, p);
		
		// If piece is butterfly, record the location
		if (p.getType() == HantoPieceType.BUTTERFLY) {
			if(p.getColor() == HantoPlayerColor.BLUE) {
				blueBLoc = c;
			}
			else {
				redBLoc = c;						
			}
		}
	}
	
	/**
	 * @param c
	 */
	public void removePieceOn (HantoCoordinateImpl c) {
		
		HantoPiece p = board.get(c);
		if (p != null) {
			board.remove(c);
		}
	}
	
	public void clear () {
		board.clear();
	}
	
	/**
	 * @return boardState
	 */
	public String getPrintableBoard() {
		String boardState = "";
		for (HantoCoordinate key: board.keySet()) {
			HantoPiece p = board.get(key);
			if (p != null) {
				boardState += p.getColor().toString() + " " + p.getType().toString() + " at (" + key.getX() + ", " + key.getY() + ")\n"; 
			}
		}
		return boardState;
	}
	
	/**
	 * Method isDestinationAdjacentToAnother.
	 * @param loc HantoCoordinate
	 * @return boolean */
	public boolean isDestinationAdjacentToAnother (HantoCoordinate loc) {
		if(loc == null) {
			return false;
		}
		
		List<HantoCoordinateImpl> connected = adjacentPieceCoords(loc);
		
		return connected.size() > 0;
	}
	
	/**
	 * Method adjacentPieceCoords
	 * @param loc
	
	 * @return adjacent */
	public List<HantoCoordinateImpl> adjacentPieceCoords (HantoCoordinate loc) {
		HantoCoordinateImpl myLoc = new HantoCoordinateImpl(loc);
		
		List<HantoCoordinateImpl> adjacent = new ArrayList<HantoCoordinateImpl>();
		List<HantoCoordinateImpl> neighbors = myLoc.getNeighbors();
		HantoPiece[] piece = new HantoPiece[6];
		
		for(int i = 0; i < 6; i++) {
			piece[i] = board.get(neighbors.get(i));
			if (piece[i] != null) {
				adjacent.add(neighbors.get(i));
			}
		}
		
		return adjacent;
	}
	
	/**
	 * Method isSurrounded.
	 * @param loc HantoCoordinate
	 * @return boolean */
	protected boolean isSurrounded(HantoCoordinate loc) {
		if(loc == null) {
			return false;
		}
		
		List<HantoCoordinateImpl> connected = adjacentPieceCoords(loc);
		return connected.size() == 6;
	}
	
	private synchronized void constructConnectedPiecesList (List<HantoCoordinateImpl> visited, HantoCoordinate parent) {
		List<HantoCoordinateImpl> connected = adjacentPieceCoords(parent);
		connected.removeAll(visited);
		
		for (int j = 0; j < connected.size(); j++) {
			visited.add(connected.get(j));
		}
		
		Set<HantoCoordinateImpl> visitedH = new HashSet<HantoCoordinateImpl>();
		visitedH.addAll(visited);
		visited.clear();
		visited.addAll(visitedH);
		
		for (int k = 0; k < connected.size(); k++) {
			constructConnectedPiecesList (visited, connected.get(k));
		}
	}
	
	/**
	 * @return count
	 */
	public int numOfPiecesOnBoard () {
		int count = 0;
		Collection<HantoPieceImpl> pList = board.values();
		
		for (HantoPiece p : pList) {
			if (p != null) {
				count++;
			}
		}
		
		return count;		
	}
	
	/**
	 * @param from
	 * @param to
	
	 * @return boolean
	 */
	public boolean isContinuous (HantoCoordinate from, HantoCoordinate to) {
		
		boolean continuous = false;
		int numPieces = numOfPiecesOnBoard();
		List<HantoCoordinateImpl> visited = new ArrayList<HantoCoordinateImpl>();
		
		HantoCoordinateImpl myTo = new HantoCoordinateImpl(to);
		HantoCoordinateImpl myFrom = new HantoCoordinateImpl(from);
		
		HantoPieceImpl p = getPieceAt(myFrom);
		
		removePieceOn(myFrom);
		putPieceOn(myTo, p);
		
		List<HantoCoordinateImpl> connected = adjacentPieceCoords(from);
		visited.add(connected.get(0));
		constructConnectedPiecesList (visited, connected.get(0));
		
		if (visited.size() == numPieces) {
			continuous = true;
		}
		
		removePieceOn(myTo);
		putPieceOn(myFrom, p);
		
		return continuous;
	}
	
}
