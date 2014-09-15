package hanto.studentdbtrue.alpha;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public class HantoPieceImpl implements HantoPiece {

	private HantoPlayerColor color;
	private HantoPieceType type;
	
	public HantoPieceImpl( HantoPlayerColor color, HantoPieceType type ) {
		this.color = color;
		this.type = type;
	}
	
	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	@Override
	public HantoPieceType getType() {
		return type;
	}

}
