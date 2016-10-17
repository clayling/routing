package org.clay.routing.layout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.clay.routing.order.IOrder;
import org.clay.routing.struct.Item;
import org.clay.routing.struct.Position;

public class DoubleLayout implements ILayout<DoublePickAisle>{
	
	
	private int nBlock;
	
	/**
	 * blocks集合
	 */
	private List< Block0<DoublePickAisle> > blocks ;
	
	
	/**
	 * 构造方法
	 * @param nBlock block数量
	 * @param nPick 拣货通道数量
	 */
	public DoubleLayout(int nBlock, int nAisle, int aisleLength){
//		this.layout = layout;
		this.nBlock = nBlock;
		blocks = new ArrayList< Block0<DoublePickAisle> >(this.nBlock);
//		for(int i = 0; i < this.nBlock; i++){
//			Block<DoublePickAisle> block = new Block<DoublePickAisle>(nAisle);
//			blocks.add(block);
//		}
	}
	
	public int nBlock() {
		return nBlock;
	}

	

	public boolean doublePick() {
		return true;
	}

	
	
	public void setBlock(int i, Block0<DoublePickAisle> block){
		this.blocks.set(i, block);
	}
	
	public Block0<DoublePickAisle> getBlock(int i){
		return this.blocks.get(i);
	}
	
	public void initialBlock(){
		
	}
	
	
}
