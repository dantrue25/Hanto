package hanto.studentdbtrue.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import java.util.HashMap;
import java.util.Map;

public class AlphaHantoGame implements HantoGame {

	private Map<HantoCoordinateImpl, HantoPiece> board = 
			new HashMap<HantoCoordinateImpl, HantoPiece>();
	
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
			// Check board for pieces surrounding new piece
			HantoPiece adj1 = board.get(new HantoCoordinateImpl(myTo.getX()    , myTo.getY() - 1));
			HantoPiece adj2 = board.get(new HantoCoordinateImpl(myTo.getX()    , myTo.getY() + 1));
			HantoPiece adj3 = board.get(new HantoCoordinateImpl(myTo.getX() - 1, myTo.getY()    ));
			HantoPiece adj4 = board.get(new HantoCoordinateImpl(myTo.getX() + 1, myTo.getY()    ));
			HantoPiece adj5 = board.get(new HantoCoordinateImpl(myTo.getX() - 1, myTo.getY() + 1));
			HantoPiece adj6 = board.get(new HantoCoordinateImpl(myTo.getX() + 1, myTo.getY() - 1));
			
			// Check if there is an adjacent piece, and if not, throw exception
			if(!(adj1 == null && adj2 == null && adj3 == null && adj4 == null && adj5 == null && adj6 == null)) {
				result = MoveResult.DRAW;
			}
			else {
				throw new HantoException("Invalid Move");
			}
		}
		
		HantoPiece p = new HantoPieceImpl( pieceType, currentTurn );
		
		if( currentTurn == HantoPlayerColor.BLUE)
			currentTurn = HantoPlayerColor.RED;
		else
			currentTurn = HantoPlayerColor.BLUE;
		
		board.put(myTo, p);
		
		return result;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		
		HantoCoordinateImpl myWhere = new HantoCoordinateImpl(where);
		
		return board.get(myWhere);
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
