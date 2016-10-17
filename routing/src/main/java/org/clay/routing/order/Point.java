package org.clay.routing.order;

import java.util.Map;


/**
 * 拣货点。同一个拣货点可以有多个拣货位置，一般是一个或者两个。
 * @author clay
 *
 */
public class Point<I> extends IntegerKeyMap< I > {

	
	
	/**
	 * 增加拣货
	 * @param sideNo
	 */
	public void addItem(int sideNo, I data){
		map.put(sideNo, data);
	}
	
	/**
	 * 拣货
	 * @param sideNo
	 */
	public void pick(int sideNo){
		if( this.needPicking(sideNo) == false)
			return;
		map.remove(sideNo);
	}
	
	/**
	 * 是否有需要拣货
	 * @param sideNo
	 * @return
	 */
	public boolean needPicking(int sideNo){
		I cell = map.get(sideNo);
		if(cell == null)
			return false;
		return true;
	}
}
