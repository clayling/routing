package org.clay.routing.layout;

import java.util.ArrayList;

import org.clay.routing.struct.Item;

/**
 * 两边都能拣货的通道
 * @author clay
 *
 */
public class DoublePickAisle implements IPickAisle{
	/**
	 * 所属block
	 */
	private Block0<DoublePickAisle> block;
	/**
	 * 通道左边的货物
	 */
	private ArrayList< Item > leftItems;
	/**
	 * 通道右边的货物
	 */
	private ArrayList< Item > rightItems;
	
	private int length;
	
	/**
	 * 构造时必须提供长度
	 * @param length
	 */
	public DoublePickAisle( int length){
//		this.block = block;
//		Block<DoublePickAisle>[] data =(Block<DoublePickAisle>[]) new Block<?>[10];
		this.length = length;
		leftItems = new ArrayList< Item >(length);
		rightItems = new ArrayList< Item >(length);
	}
	
	

	public void addOrderItem(int index, boolean right, Item item) {
		if(!right)
			this.putLeft(index, item);
		else
			this.putLeft(index, item);
		
	}
	

	public Item getOrderItem(int index, boolean right) {
		if(!right)
			return this.getLeft(index);
		else
			return this.getRight(index);
	}

	public void putLeft(int index, Item item){
		leftItems.set(index, item);
	}
	
	public Item getLeft(int index){
		return leftItems.get(index);
	}
	
	public void putRight(int index, Item item){
		rightItems.set(index, item);
	}
	
	public Item getRight(int index){
		return rightItems.get(index);
	}
	
	
	
	public boolean isAllPicked(){
		for(int i = 0; i < this.length; i++){
			if(this.isPicked(i) == false)
				return false;
		}
		return true;
	}
	
	public boolean isPicked(int index){
		//两边都不需要拣取，才代表这个编号的位置被拣取了
		return !leftItems.get(index).needsPicking() && !rightItems.get(index).needsPicking();
	}
	
	
	public void pick(int index){
		Item left = leftItems.get(index);
		if(left.isInOrder())
			left.setPicked(true);
		Item right = rightItems.get(index);
		if(right.isInOrder())
			right.setPicked(true);
	}
	
	/** 
	 * 设置通道上所有货物为已拣取
	 */
	public void pickAll(){
		for(int i = 0; i < leftItems.size(); i++){
			this.pick(i);
		}
	}
	
	
	public Item getMinItem(){
		for(int i = 0; i < leftItems.size(); i++){
			Item item = leftItems.get(i);
			if( item.needsPicking() == true )
				return item;
		}
		return null;
	}

	public int getLength() {
		return length;
	}

	


//	public IPickAisle builder(int length) {
//		IPickAisle instance = new DoublePickAisle(length);
//		return instance;
//		
//	}

	
	
}
