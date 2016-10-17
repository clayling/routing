package org.clay.routing.order;


public class Order<I> extends IntegerKeyMap< Block<I> >{	
	
	/**
	 * 订单中 增加/更新 一个货物
	 * @param blockNo
	 * @param aisleNo
	 * @param pointNo
	 * @param sideNo
	 * @param item
	 */
	public void add(int blockNo, int aisleNo, int pointNo, int sideNo, I item){
		Block<I> block = this.get(blockNo);
		if(block == null){
			block = new Block<I>();
		}
		Aisle<I> aisle = block.get(aisleNo);
		if(aisle == null){
			aisle = new Aisle<I>();
		}
		Point<I> point = aisle.get(pointNo);
		if(point == null){
			point = new Point<I>();
		}
		point.put(sideNo, item);
		aisle.put(pointNo, point);
		block.put(aisleNo, aisle);
		this.put(blockNo, block);	
	}
	
	/** 
	 * 获取订单中一个货物
	 * @param blockNo
	 * @param aisleNo
	 * @param pointNo
	 * @param sideNo
	 * @return
	 */
	public I get(int blockNo, int aisleNo, int pointNo, int sideNo){
		Block<I> block = this.get(blockNo);
		if(block == null){
			return null;
		}
		Aisle<I> aisle = block.get(aisleNo);
		if(aisle == null){
			return null;
		}
		Point<I> point = aisle.get(pointNo);
		if(point == null){
			return null;
		}
		return point.get(sideNo);
	}
	
	/**
	 * 移除订单中的一个货物
	 * @param blockNo
	 * @param aisleNo
	 * @param pointNo
	 * @param sideNo
	 */
	public void remove(int blockNo, int aisleNo, int pointNo, int sideNo){
		Block<I> block = this.get(blockNo);
		if(block == null){
			return ;
		}
		Aisle<I> aisle = block.get(aisleNo);
		if(aisle == null){
			return ;
		}
		Point<I> point = aisle.get(pointNo);
		if(point == null){
			return ;
		}
		point.remove(sideNo);
		if(point.isEmpty()){
			aisle.remove(pointNo);
			if(aisle.isEmpty()){
				block.remove(aisleNo);
				if(block.isEmpty())
					this.remove(blockNo);
			}
		}
		
	}
	
	/**
	 * 移除订单中的一个通道上的货物
	 * @param blockNo
	 * @param aisleNo
	 * @param pointNo
	 * @param sideNo
	 */
	public void removeAisle(int blockNo, int aisleNo){
		Block<I> block = this.get(blockNo);
		if(block == null){
			return ;
		}
		Aisle<I> aisle = block.get(aisleNo);
		if(aisle == null){
			return ;
		}
		block.remove(aisleNo);
		if(block.isEmpty()){
			this.remove(blockNo);
		}	
	}
	
	public int leftPickAisle(){
		int min = Integer.MAX_VALUE;
		for(Block<I> block: this.map.values()){
			int blockMin = block.getMinNo();
			if(blockMin < min)
				min = blockMin;
		}
		return min;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int blockNo: this.map.keySet()){
			Block<I> block = this.get(blockNo);
			for(int aisleNo: block.map.keySet()){
				Aisle<I> aisle = block.get(aisleNo);
				for(int pointNo: aisle.map.keySet()){
					Point<I> point = aisle.get(pointNo);
					for(int sideNo: point.map.keySet()){
						I item = point.get(sideNo);
						sb.append("["+blockNo+" "+aisleNo+" "+pointNo+" "+sideNo+"]\n");
					}
				}
			}
				
		}
		return sb.toString();
	}

	
	
//	public int farthestBlock(){
//		int result = Integer.MAX_VALUE;
//		for(int blockNo: this.map.keySet()){
//			if(blockNo < result)
//				result = blockNo;
//		}
//		return result;
//	}
	
}
