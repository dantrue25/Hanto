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

import java.util.ArrayList;
import java.util.List;

/**
 */
public class HantoCoordinateImpl implements HantoCoordinate {
	
	private int x;
	private int y;

	/**
	 * Constructor for HantoCoordinateImpl.
	 * @param x int
	 * @param y int
	 */
	public HantoCoordinateImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor for HantoCoordinateImpl.
	 * @param c HantoCoordinate
	 */
	public HantoCoordinateImpl(HantoCoordinate c) {
		if (c != null) {
			x = c.getX();
			y = c.getY();
		}
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	/**
	 * @return neighbors
	 */
	public List<HantoCoordinateImpl> getNeighbors () {
		List<HantoCoordinateImpl> neighbors = new ArrayList<HantoCoordinateImpl>();
		
		neighbors.add(new HantoCoordinateImpl(this.getX() - 1, this.getY() + 1));
		neighbors.add(new HantoCoordinateImpl(this.getX() - 1, this.getY()));
		neighbors.add(new HantoCoordinateImpl(this.getX(), this.getY() + 1));
		neighbors.add(new HantoCoordinateImpl(this.getX(), this.getY() - 1));
		neighbors.add(new HantoCoordinateImpl(this.getX() + 1, this.getY()));
		neighbors.add(new HantoCoordinateImpl(this.getX() + 1, this.getY() - 1));
		
		return neighbors;
	}
	
	/**
	 * @param other
	 * @return myNeighbors
	 */
	public List<HantoCoordinateImpl> getSharedNeighbors (HantoCoordinateImpl other) {
		List<HantoCoordinateImpl> myNeighbors = this.getNeighbors();
		List<HantoCoordinateImpl> otherNeighbors = other.getNeighbors();
		
		myNeighbors.retainAll(otherNeighbors);
		return myNeighbors;
	}
	
	/**
	 * 
	 * @param other
	 * @return coordsInbetween
	 */
	public List<HantoCoordinateImpl> getCoordsInbetween (HantoCoordinateImpl other) {
		List<HantoCoordinateImpl> coordsInbetween = new ArrayList<HantoCoordinateImpl>();
		
		int deltaX = this.getX() - other.getX();
		int deltaY = this.getY() - other.getY();
		
		boolean isDiagonal = (deltaY == -1 * deltaX) && deltaY != 0;
		boolean isSingleDirection = ((deltaX == 0) != (deltaY == 0));
		
		if (isDiagonal && deltaX > 0) {
			for (int i = 1; i < deltaX; i++) {
				coordsInbetween.add(new HantoCoordinateImpl(other.getX() + i, other.getY() - i));
			}
		}
		else if (isDiagonal && deltaY > 0) {
			for (int i = 1; i < deltaY; i++) {
				coordsInbetween.add(new HantoCoordinateImpl(this.getX() + i, this.getY() - i));
			}
		}
		else if (isSingleDirection && deltaX > 0) {
			for (int i = 1; i < deltaX; i++) {
				coordsInbetween.add(new HantoCoordinateImpl(other.getX() + i, other.getY()));
			}
		}
		else if (isSingleDirection && deltaX < 0) {
			for (int i = 1; i < Math.abs(deltaX); i++) {
				coordsInbetween.add(new HantoCoordinateImpl(this.getX() + i, this.getY()));
			}
		}
		else if (isSingleDirection && deltaY > 0) {
			for (int i = 1; i < deltaY; i++) {
				coordsInbetween.add(new HantoCoordinateImpl(other.getX(), other.getY() + i));
			}
		}
		else if (isSingleDirection && deltaY < 0) {
			for (int i = 1; i < Math.abs(deltaY); i++) {
				coordsInbetween.add(new HantoCoordinateImpl(this.getX(), this.getY() + i));
			}
		}
		
		return coordsInbetween;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

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
