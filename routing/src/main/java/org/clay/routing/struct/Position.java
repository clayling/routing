package org.clay.routing.struct;

public class Position {
	
	/**
	 * 在哪个block
	 */
	private int block;
	
	/**
	 * 在第几条拣货通道
	 */
	private int pickAisle;
	
	/**
	 * 在第几行
	 */
	private int row;
	
	/**
	 * 在通道左边还是右边
	 */
	private boolean right;
	
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public int getPickAisle() {
		return pickAisle;
	}
	public void setPickAisle(int pickAisle) {
		this.pickAisle = pickAisle;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	
	
	
	
}
