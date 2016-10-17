package org.clay.routing.struct;

/**
 * 仓库布局中的一个位置。
 * @author clay
 *
 */
public class Item {
	
	/**
	 * 货物的位置
	 */
	Position position;
	
	/**
	 * 是否在订单中。不在订单中的时候不需要考虑拣货。
	 */
	boolean inOrder;
	
	/**
	 * 是否完成拣货
	 */
	boolean picked;
	//other data
	//....
	
	public Item(){
		this.position = null;
		this.inOrder = false;
		this.picked = false;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

//	public boolean isPicked() {
//		return picked;
//	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}

	public boolean isInOrder() {
		return inOrder;
	}

	public void setInOrder(boolean inOrder) {
		this.inOrder = inOrder;
	}
	
	/**
	 * 只有在订单中，并且还没拣取的货物才需要被拣取
	 * @return
	 */
	public boolean needsPicking(){
		if(this.inOrder && this.picked == false)
			return true;
		return false;
	}
	
	
}
