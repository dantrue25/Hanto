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

	private Map<HantoCoordinate, HantoPiece> board = 
			new HashMap<HantoCoordinate, HantoPiece>();
	
	private HantoPlayerColor currentTurn = HantoPlayerColor.BLUE;
	
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		if( pieceType != HantoPieceType.BUTTERFLY ) {
			throw new HantoException( "Invalid piece" );
		}
		
		if( board.isEmpty() ) {
			if( to.getX() != 0 && to.getY() != 0) {
				throw new HantoException( "Invalid coordinate" );
			}
		}
		
		if( from != null ) {
			throw new HantoException( "Cannot move piece" );
		}
		
		HantoPiece p = new HantoPieceImpl( pieceType, currentTurn );
		
		if( currentTurn == HantoPlayerColor.BLUE)
			currentTurn = HantoPlayerColor.RED;
		else
			currentTurn = HantoPlayerColor.BLUE;
		
		board.put(to, p);
		
		return MoveResult.OK;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		
		return board.get(where);
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
