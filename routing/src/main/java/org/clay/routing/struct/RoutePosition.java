package org.clay.routing.struct;

/**
 * 路线的一个点。有两种类型：block中，交叉通道上。
 * @author clay
 *
 */
public class RoutePosition {

	public static final int BLOCK_POSITION = 0;
	public static final int CROSS_POSITION = 1;
	public static final int DEPOT =2;
	
	/** 公共数据 */
	private int type;
	
	private int pickAisle;

	/** block中的位置*/
	private int block;
	
	private int row;
	
	/** cross aislle上的位置*/
	private int crossAisle;
	
	public RoutePosition(int type){
		this.type = type;
	}
	
	public RoutePosition(int type, int pickAisle, int block, int row, int crossAisle){
		this.type = type;
		this.pickAisle = pickAisle;
		if(type == BLOCK_POSITION){
			this.block = block;
			this.row = row;
		}
		else if(type == CROSS_POSITION){
			this.crossAisle = crossAisle;
		}
	}
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPickAisle() {
		return pickAisle;
	}

	public void setPickAisle(int pickAisle) {
		this.pickAisle = pickAisle;
	}

	public int getBlock() {
		if(type == BLOCK_POSITION)
			return block;
		else 
			return -1;
	}

	public void setBlock(int block) {
		if(type == BLOCK_POSITION)
			this.block = block;
	}

	public int getRow() {
		if(type == BLOCK_POSITION)
			return row;
		else 
			return -1;
	}

	public void setRow(int row) {
		if(type == BLOCK_POSITION)
			this.row = row;
	}

	public int getCrossAisle() {
		if(type == CROSS_POSITION)
			return crossAisle;
		else
			return -1;
	}

	public void setCrossAisle(int crossAisle) {
		if(type == CROSS_POSITION)
			this.crossAisle = crossAisle;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Type:");
		if(this.type == DEPOT){
			sb.append("DEPOT.");
		}
		else if(this.type == CROSS_POSITION){
			sb.append("CROSS POSITION.");
			sb.append(" Pick Aisle: "+ this.pickAisle);
			sb.append(" Cross Aisle: "+this.crossAisle);
		}
		else if(this.type == BLOCK_POSITION){
			sb.append("BLOCK POSITION.");
			sb.append(" Pick Aisle: "+ this.pickAisle);
			sb.append(" Block: "+ this.block);
			sb.append(" Row: "+this.row);
		}
		return sb.toString();
	}
	
	
}
