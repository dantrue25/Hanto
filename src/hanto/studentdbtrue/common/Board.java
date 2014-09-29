/**
 * 
 */
package hanto.studentdbtrue.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Dan
 *
 */
public class Board {
	
	private Map<HantoCoordinate, HantoPiece> board;
	protected HantoCoordinateImpl blueBLoc = null;
	protected HantoCoordinateImpl redBLoc = null;
	
	/**
	 * Constructor for Board.
	 */
	public Board () {
		board = new HashMap<HantoCoordinate, HantoPiece>();
	}
	
	/**
	 * @return true if board is empty
	 */
	public boolean isEmpty () {
		return board.isEmpty();
	}
	
	/**
	 * @param where HantoCoordinate
	 * @return HantoPiece 
	 */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		
		HantoCoordinateImpl myWhere = new HantoCoordinateImpl(where);
		
		return board.get(myWhere);
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
	 * @return boardState
	 */
	public String getPrintableBoard() {
		String boardState = "";
		for (HantoCoordinate key: board.keySet()) {
		    HantoPiece p = board.get(key);
			boardState += p.getColor().toString() + " " + p.getType().toString() + " at (" + key.getX() + ", " + key.getY() + ")\n"; 
		}
		return boardState;
	}
	
	/**
	 * Method getMoveResult.
	 * @param bluePlayer PlayerState
	 * @param redPlayer PlayerState
	 * @return MoveResult
	 */
	public MoveResult getMoveResult (PlayerState bluePlayer, PlayerState redPlayer) {
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
	 * @return adjacent
	 */
	public List<HantoCoordinateImpl> adjacentPieceCoords (HantoCoordinate loc) {
		HantoCoordinateImpl myLoc = new HantoCoordinateImpl(loc);
		
		ArrayList<HantoCoordinateImpl> adjacent = new ArrayList<HantoCoordinateImpl>();
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
	
	private synchronized void constructConnectedPiecesList (List<HantoCoordinateImpl> visited, HantoCoordinate parent, int count) {
		List<HantoCoordinateImpl> connected = adjacentPieceCoords(parent);
		count++;
		for (int i = 0; i < connected.size(); i++) {
			if (visited.contains(connected.get(i))) {
				System.out.println("   Removing: (" + connected.get(i).getX() + ", " + connected.get(i).getY() + ")");
				
				connected.remove(connected.get(i));
			}
		}
		
		for (int j = 0; j < connected.size(); j++) {
			visited.add(connected.get(j));
		}
		
		HashSet<HantoCoordinateImpl> visitedH = new HashSet<HantoCoordinateImpl>();
		visitedH.addAll(visited);
		visited.clear();
		visited.addAll(visitedH);
		
		System.out.println("count: " + count);
		for (int n = 0; n < visited.size(); n++) {
			System.out.println("(" + visited.get(n).getX() + ", " + visited.get(n).getY() + ")");
		}
		System.out.println("");
		
		for (int k = 0; k < connected.size(); k++) {
			constructConnectedPiecesList (visited, connected.get(k), count);
		}
	}
	
	private int numOfPiecesOnBoard () {
		int count = 0;
		Collection<HantoPiece> pList = board.values();
		
		for (HantoPiece p : pList) {
			if (p != null) {
				count++;
			}
		}
		
		return count;		
	}
	
	/**
	 * @param moving
	 * @return continuous
	 */
	public boolean isContinuous (HantoCoordinate moving) {
		boolean continuous = false;
		List<HantoCoordinateImpl> visited = new ArrayList<HantoCoordinateImpl>();
		
		HantoPiece p = board.get(moving);
		board.remove(moving);
		
		List<HantoCoordinateImpl> connected = adjacentPieceCoords(moving);
		visited.add(connected.get(0));
		int count = 0;
		constructConnectedPiecesList (visited, connected.get(0), count);
		
		System.out.println("V size = " + visited.size() + ", B size = " + numOfPiecesOnBoard());
		
		if (visited.size() == numOfPiecesOnBoard()) {
			continuous = true;
		}
		
		board.put(moving, p);
		return continuous;
	}
	
}
