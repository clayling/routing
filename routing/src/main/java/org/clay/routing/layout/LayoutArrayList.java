package org.clay.routing.layout;

import java.util.ArrayList;

public class LayoutArrayList<T> {

	private ArrayList<T> data;
	
	
	public T get(int index){
		return data.get(index);
	}
	
	public T set(int index, T value){
		return data.set(index, value);
	}
	
	public int size(){
		return data.size();
	}
}

