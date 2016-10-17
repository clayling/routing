package org.clay.routing.layout;

import java.util.ArrayList;


import org.clay.routing.struct.Item;

/**
 * 
 * @author clay
 *
 * @param <T> 拣货通道的类型，两边都可以拣货，或者只支持一边拣货
 */
public class Block0<T extends IPickAisle>{
	
	/**
	 * block的编号
	 */
	private int no;
	
	/**
	 * block的宽度, 即拣货通道的数量
	 */
	private int width;
		
	
	/**
	 * 拣货通道集合
	 */
	private ArrayList<T> pickAisles;
	
	
	public Block0(int width){
		this.width = width;
		pickAisles = new ArrayList<T>(this.width);
		
	}
	
	
	
	public Block0(int width, int length, int no){
		this.width = width;
		pickAisles = new ArrayList<T>(this.width);
		this.no = no;
	}
	
	
	/**
	 * 把item放到block中的对应位置
	 * @param pickAisleNo 拣货通道序号
	 * @param index 通道中的序号
	 * @param right 是否放在通道的右边
	 * @param item 货物
	 */
	public void putItem(int pickAisleNo, int index, boolean right, Item item){
		T pickAisle = this.getPickAisle(pickAisleNo);
		pickAisle.addOrderItem(index, right, item);
	}
	
	
	
	public Item getItem(int pickAisleNo, int index, boolean right, Item item){
		T pickAisle = this.getPickAisle(pickAisleNo);
		return pickAisle.getOrderItem(index, right);
	}
	
	/**
	 * 是否有未完成拣货的货物
	 * @param pickAisleNo
	 * @return
	 */
	public boolean hasItems(int pickAisleNo){
		T pickAisle = pickAisles.get(pickAisleNo);
		return !pickAisle.isAllPicked();
	}
	
	/**
	 * 在交叉通道上，找到右边第一个有货物的拣货通道
	 * @return
	 */
	public int leftAisleWithItems(){
		for(int i = 0; i < this.width; i++ ){
			if(this.hasItems(i) == true)
				return i;
		}
		return -1;
	}
	
	/**
	 * 是否存在其他有货物的拣货通道
	 * @param aisleNo
	 * @return
	 */
	public boolean moreAisleWithItems(int aisleNo){
		for(int i = 0; i < this.width; i++){
			if(i == aisleNo)
				continue;
			if(this.hasItems(i) == true)
				return true;
		}
		return false;
	}
	
	/**
	 * 获取通道上最小编号的货物
	 * @param aisleNo
	 * @return
	 */
	public Item getMinItem(int aisleNo){
		T pickAisle = pickAisles.get(aisleNo);
		return pickAisle.getMinItem();
	}

	/**
	 * 所有拣货通道是否都完成拣货
	 * @return
	 */
	public boolean isAllPicked(){
		for(T pickAisle:pickAisles){
			if(pickAisle.isAllPicked() == false)
				return false;
		}
		return true;
	}
	
	/**
	 * 最左边的还有拣货任务的通道
	 * @return
	 */
	public int leftMostPickAisleWithItems(){
		int size = pickAisles.size();
		for(int i = 0; i < size; i++){
			if(!pickAisles.get(i).isAllPicked())
				return i;
		}
		return -1;
	}
	
	/**
	 * 最右边的还有拣货任务的通道
	 * @return
	 */
	public int rightMostPickAisleWithItems(){
		int size = pickAisles.size();
		for(int i = size-1; i >= 0; i--){
			if(!pickAisles.get(i).isAllPicked())
				return i;
		}
		return -1;
	}
	
	/**
	 * block中还有多少通道没有完成拣货
	 * @return
	 */
	public int unpickedAisleNumber(){
		int count = 0;
		for(int i = 0; i < pickAisles.size(); i++){
			if(pickAisles.get(i).isAllPicked() == false)
				count++;
		}
		return count;
	}
	
	//getter and setter
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public T getPickAisle(int index){
		return pickAisles.get(index);
	}
	
	public void setPickAisle(int index, T pickAisle){
		pickAisles.set(index, pickAisle);
	}
	
	public IPickAisle getNextRightPickAisleNeedsPick(){
		for(int i = 0; i < pickAisles.size(); i++){
			T pickAisle = pickAisles.get(i);
			if( pickAisle.isAllPicked() == false )
				return pickAisle;	
		}
		return null;
	}
	
	public IPickAisle getNextLeftPickAisleNeedsPick(){
		for(int i = pickAisles.size() - 1; i >= 0; i--){
			T pickAisle = pickAisles.get(i);
			if( pickAisle.isAllPicked() == false )
				return pickAisle;	
		}
		return null;
	}
	
	public void pickAisle(int pickAisleNo){
		IPickAisle pickAisle = this.getPickAisle(pickAisleNo);
		pickAisle.pickAll();	
	}
	
	
}
