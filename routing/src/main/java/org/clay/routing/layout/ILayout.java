package org.clay.routing.layout;


import org.clay.routing.order.IOrder;

public interface ILayout<T extends IPickAisle> {

	/**
	 * block数量
	 * @return
	 */
	int nBlock();
	
	
	/**
	 * 是否支持通道两边拣货
	 * @return
	 */
	boolean doublePick();
	
	
	
	/**
	 * 获得指定block
	 * @param i
	 * @return
	 */
	Block0<T> getBlock(int i);
}
