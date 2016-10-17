package org.clay.routing.order;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.clay.routing.SubRouteBuilder;

import org.clay.routing.struct.RoutePosition;
import org.clay.routing.struct.SubRoute;

public class SShape {
	Logger log = Logger.getLogger("shape.class");
	public<I> List<SubRoute> route(int nBlock, Order<I> order){
		
		List<SubRoute> routeList = new ArrayList<SubRoute>();
		//left most pick aisle
		int leftPickAisle = order.leftPickAisle();
		//farthest blcok 
		int farthestBlockNo = order.getMinNo();
		
		//子路线1:从depot 到 最左拣货通道的前面
		RoutePosition depot = new RoutePosition(RoutePosition.DEPOT);
		int nCrossAisle = nBlock + 1;
		RoutePosition p1 = new RoutePosition(RoutePosition.CROSS_POSITION, 
				leftPickAisle, -1, -1, nCrossAisle - 1);
		SubRoute r1 = new SubRoute(depot, p1);
		routeList.add(r1);
		
		//子路线2:从最左拣货通道的前面 到 最远block的前面
		RoutePosition p2 = new RoutePosition(RoutePosition.CROSS_POSITION, 
				leftPickAisle, -1, -1, farthestBlockNo+1);
		SubRoute r2 = new SubRoute(p1,p2);
		routeList.add(r2);
		//拣取路上所有货物，即每个block中该通道上的货物
		for(int i = 1; i < nBlock; i++){
			order.removeAisle(i, leftPickAisle);
		}
		//最远block
		Block<I> farthestBlock = order.get(farthestBlockNo);
		//最远block中第一个有货物的拣货通道
//		int farthestBlockPickAisleNo = farthestBlock.leftAisleWithItems(leftPickAisle);
		int farthestBlockPickAisleNo = farthestBlock.getMinNo();
		//子路线3:从最远block的前面 到 该交叉通道上第一个有货物的拣货通道的前面
		
		RoutePosition p3 = new RoutePosition(RoutePosition.CROSS_POSITION, 
				farthestBlockPickAisleNo, -1, -1, farthestBlockNo+1);
		SubRoute r3 = new SubRoute(p2,p3);
		routeList.add(r3);
		
		//当前位置
		RoutePosition current;

		//其他有货物的拣货通道是否存在
//		boolean more = farthestBlock.moreAisleWithItems(farthestBlockPickAisleNo);
		boolean more = farthestBlock.size() != 0;
		if(more){
			RoutePosition p4 = new RoutePosition(RoutePosition.CROSS_POSITION,
					farthestBlockPickAisleNo,
					-1,-1,
					farthestBlockNo);
			SubRoute r4 = new SubRoute(p3,p4);
			routeList.add(r4);
			order.removeAisle(farthestBlockNo,farthestBlockPickAisleNo);
			current = p4;
		}
		//没有其他拣货通道，取完最后一个货物后返回
		else{
			//最远的货物就是序号最小的货物
			RoutePosition p41 = new RoutePosition(RoutePosition.BLOCK_POSITION,
					farthestBlockPickAisleNo,-1,-1,
					farthestBlockNo);
			Aisle<I> farthestBlockPickAisle = farthestBlock.get(farthestBlockPickAisleNo);
			int minNo =  farthestBlockPickAisle.getMinNo();
			p41.setRow(minNo);
			SubRoute r41 = new SubRoute(p3,p41);
			routeList.add(r41);
			SubRoute r42 = new SubRoute(p41, p3);
			routeList.add(r42);
			order.removeAisle(farthestBlockNo,farthestBlockPickAisleNo);
			current = p3;
		}
		int currentBlockNo = farthestBlockNo;
		//循环访问每一个block
		while(true){
			//当前block
			Block<I> currentBlock = order.get(currentBlockNo);
			//检查是否拣完
			boolean allPicked = currentBlock.isEmpty();
			if(allPicked){
				//到下一个交叉通道
				RoutePosition p5 = new RoutePosition(RoutePosition.CROSS_POSITION, 
						current.getPickAisle(), -1, -1, current.getCrossAisle()+1);
				SubRoute r5 = new SubRoute(current, p5);
				routeList.add(r5);
				current = p5;
				currentBlockNo ++;
				
			}
			//block中还有其他货物要拣取
			else{
				//每个循环处理一条拣货通道
				while(currentBlock.size() > 1){
					int leftMostPickAisleNo = currentBlock.getMinNo();
					int rightMostPickAisleNo = currentBlock.getMaxNo();
					int currentPickAisleNo = current.getPickAisle();
					//下一个拣货通道的起点和终点
					RoutePosition start,end;
					//去右边更近
					boolean left = this.isCloseToLeft(currentPickAisleNo, leftMostPickAisleNo, rightMostPickAisleNo);
					if( !left ){
						start = new RoutePosition(RoutePosition.CROSS_POSITION, 
								rightMostPickAisleNo, -1, -1, current.getCrossAisle());
					}
					else{
						start = new RoutePosition(RoutePosition.CROSS_POSITION, 
								leftMostPickAisleNo, -1, -1, current.getCrossAisle());
					}
					SubRoute r = new SubRoute(current, start);
					routeList.add(r);
					//穿过整个子通道
					
					int crossNo = start.getCrossAisle();
					//从上往下穿过
					if(currentBlockNo == crossNo){
						end = new RoutePosition(RoutePosition.CROSS_POSITION, 
								start.getPickAisle(), -1, -1,
								start.getCrossAisle()+1);
					}
					//从下往上穿过
					else {
						 end = new RoutePosition(RoutePosition.CROSS_POSITION, 
								start.getPickAisle(), -1, -1,
								start.getCrossAisle()-1);
					}
					SubRoute through = new SubRoute(start, end);
					routeList.add(through);
					//拣取
					order.removeAisle(currentBlockNo, start.getPickAisle());
					current = end;
				}
				//处理最后一条通道
				//判断路线类型
				//穿过(往下穿过)
				int lastPickAisleNo = currentBlock.getMinNo();
				//从当前位置到最后一条通道
				current = SubRouteBuilder.crossAisle(routeList, current, lastPickAisleNo);
				if( current.getCrossAisle() == currentBlockNo ){
					current = SubRouteBuilder.pickAisle(routeList, current, current.getCrossAisle()+1);
				}
				//往返(先上再下)
				else{
//					int lastPickAisleNo = currentBlock.getMinNo();
					int minItemNo = currentBlock.get(lastPickAisleNo).getMinNo();
					SubRouteBuilder.backPickAisle(routeList, current, current.getCrossAisle()-1, minItemNo);
				}
				//拣取
				order.removeAisle(currentBlockNo, currentBlock.getMinNo());
			}
			//此时最后一条通道也处理完毕，考虑下一个block
			currentBlockNo++;
			//如果处理的是最后一个block
			if(currentBlockNo == nBlock ){
				break;
			}
			
		}
		SubRoute backToDepot = new SubRoute(current,depot);
		routeList.add(backToDepot);
		return routeList;
	}
	
	private boolean isCloseToLeft(int current, int left, int right){
		if(current < left)
			return true;
		if(current > right)
			return false;
		if(current - left > right - current)
			return false;
		else
			return true;
	}
	
	
	
	
}
