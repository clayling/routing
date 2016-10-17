package org.clay.routing.layout;

public class Layout<I> extends LayoutArrayList< Block<I> > {

	public int getNumberOfBlock(){
		return this.size();
	}
	
	public int getNumberOfAisles(){
		return -1;
	}
	
	public int getLengthOfAisles(){
		return -1;
	}
}
