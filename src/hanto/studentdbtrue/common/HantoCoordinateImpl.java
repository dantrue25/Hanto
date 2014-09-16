package hanto.studentdbtrue.common;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HantoCoordinateImpl)) {
			return false;
		}
		HantoCoordinateImpl other = (HantoCoordinateImpl) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
}
