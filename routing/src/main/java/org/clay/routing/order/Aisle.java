package org.clay.routing.order;



public class Aisle<I> extends IntegerKeyMap< Point<I> >{
	
	/**
	 * 获取通道上最小编号
	 * @param aisleNo
	 * @return
	 */
	public int getMinNo(){
		if( this.isEmpty() )
			return -1;
		int minNo = Integer.MAX_VALUE;
		for( int no: this.map.keySet() ){
			if(no < minNo)
				minNo = no;
		}
		return minNo;
	}
}
