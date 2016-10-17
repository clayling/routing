package org.clay.routing.order;

import java.util.HashMap;
import java.util.Map;

public class IntegerKeyMap<V> {

	protected Map<Integer,V> map = new HashMap<Integer, V>();
	
	public V get(int no){
		return map.get(no);
	}
	
	public V put(int no, V value){
		return map.put(no, value);
	}
	
	public V remove(int no){
		return map.remove(no);
	}
	
	public boolean isEmpty(){
		return map.isEmpty();
	}
	
	public int getMinNo(){
		if(this.isEmpty())
			return -1;
		
		int min = Integer.MAX_VALUE;
		for(int no: this.map.keySet() ){
			if(no < min)
				min = no;
		}
		return min;
	}
	
	public int getMaxNo(){
		if(this.isEmpty())
			return -1;
		
		int max = Integer.MIN_VALUE;
		for(int no: this.map.keySet() ){
			if(no > max)
				max = no;
		}
		return max;
	}
	
	public int size(){
		return this.map.size();
	}
	
}