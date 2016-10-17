package org.clay.routing;

import java.util.ArrayList;
import java.util.List;

import org.clay.routing.struct.RoutePosition;
import org.clay.routing.struct.SubRoute;

/**
 * 构造几种基本的子路线
 * @author clay
 *
 */
public class SubRouteBuilder {

	/**
	 * 构造一条横向的子路线
	 * @param start 起点
	 * @param pickAisleNo 终点的拣货通道编号
	 * @return
	 */
	public static final RoutePosition crossAisle(List<SubRoute> subRoutes,RoutePosition start, int pickAisleNo){
		RoutePosition end = new RoutePosition(RoutePosition.CROSS_POSITION);
		end.setPickAisle(pickAisleNo);
		end.setCrossAisle(start.getCrossAisle());
		SubRoute r = new SubRoute(end, start );
		subRoutes.add(r);
		return end;
	}
	
	/**
	 * 构造一条竖向的子路线
	 * @param start
	 * @param crossAisleNo
	 * @return
	 */
	public static final RoutePosition pickAisle(List<SubRoute> subRoutes,RoutePosition start, int crossAisleNo){
		RoutePosition end = new RoutePosition(RoutePosition.CROSS_POSITION);
		end.setPickAisle(start.getPickAisle());
		end.setCrossAisle(crossAisleNo);
		SubRoute r = new SubRoute(end, start );
		subRoutes.add(r);
		return end;
	}
	
	/**
	 * 构造一条来回的子路线
	 * @param subRoutes
	 * @param start
	 * @param blockNo
	 * @param rowNo
	 */
	public static final RoutePosition backPickAisle(List<SubRoute> subRoutes, RoutePosition start, int blockNo, int rowNo){
		RoutePosition mid = new RoutePosition(RoutePosition.BLOCK_POSITION);
		mid.setCrossAisle(start.getCrossAisle());
		mid.setBlock(blockNo);
		mid.setRow(rowNo);
		SubRoute r1 = new SubRoute(start, mid);
		subRoutes.add(r1);
		SubRoute r2 = new SubRoute(mid, start);
		subRoutes.add(r2);
		return start;
	}
}
