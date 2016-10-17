package org.clay.routing.layout;

public class Block<I> extends LayoutArrayList< Aisle<I> > {

	public int getNumberOfAisles(){
		return this.size();
	}
}
