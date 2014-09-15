package hanto.studentdbtrue.alpha;

import hanto.common.HantoCoordinate;

public class HantoCoordinateImpl implements HantoCoordinate {
	
	private int x;
	private int y;

	public HantoCoordinateImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public HantoCoordinateImpl(HantoCoordinate c) {
		this.x = c.getX();
		this.y = c.getY();
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	public boolean equals(Object other) {
		boolean isEqual;
		if( other == null ) {
			isEqual = false;
		}
		else if(other instanceof HantoCoordinate) {
			isEqual = ( this.getX() == ((HantoCoordinate) other).getX()
					 && this.getY() == ((HantoCoordinate) other).getY());
		}
		else {
			isEqual = false;
		}
		
		return isEqual;
	}
	
	public int hashCode() {
		int hash = 0;
		
		hash += 500*this.getX() + this.getY();
		
		return hash;
	}
	
	public boolean isAdjacentTo(HantoCoordinate other) {
		boolean isAdjacent;
		int myX = getX();
		int myY = getY();
		int otherX = other.getX();
		int otherY = other.getY();
		
		isAdjacent = (myX == otherX     && myY == otherY - 1)
				  || (myX == otherX     && myY == otherY + 1)
				  || (myX == otherX - 1 && myY == otherY    )
				  || (myX == otherX + 1 && myY == otherY    )
				  || (myX == otherX - 1 && myY == otherY + 1)
				  || (myX == otherX + 1 && myY == otherY - 1);
		
		return isAdjacent;
	}
}
