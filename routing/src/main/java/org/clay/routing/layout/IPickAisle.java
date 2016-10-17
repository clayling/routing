package org.clay.routing.layout;

import org.clay.routing.struct.Item;

/**
 * 通道的接口。有两种通道，一种是通道两边都可以拣货，另一种是只能取一边。
 * @author clay
 *
 */
public interface IPickAisle {

	
	
	/**
	 * 根据订单，增加一个拣货任务
	 * @param index 编号
	 * @param right 是否放右边
	 * @param item 货物对象
	 */
	void addOrderItem(int index, boolean right, Item item);
	
	/**
	 * 根据订单，获取一个拣货任务
	 * @param index
	 * @param right 是否是右边
	 * @return
	 */
	Item getOrderItem(int index, boolean right);
	
	
	/**
	 * 通道的长度
	 * @return
	 */
	int getLength();
	
	/**
	 * 指定编号的货物是否已经被拣取
	 * @param index
	 * @return
	 */
	boolean isPicked(int index);
	
	/**
	 * 所有货物是否全被拣取
	 * @return
	 */
	public boolean isAllPicked();
	
	/**
	 * 拣取指定编号的货物
	 * @param index
	 */
	public void pick(int index);
	
	/**
	 * 拣取所有货物
	 */
	public void pickAll();
	
	/**
	 * 获取通道上编号最小的货物
	 * @return
	 */
	public Item getMinItem();
	
//	/**
//	 * 构造一个实例
//	 * @param length
//	 * @return
//	 */
//	public IPickAisle builder(int length);
}
