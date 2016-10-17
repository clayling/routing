package org.clay.routing.struct;

public class SubRoute {
	RoutePosition start ;
	RoutePosition end ;
	
	public SubRoute(RoutePosition start, RoutePosition end){
		this.start = start;
		this.end = end;
		//System.out.println(this.toString());
	}
	
	public RoutePosition getStart() {
		return start;
	}
	public void setStart(RoutePosition start) {
		this.start = start;
	}
	public RoutePosition getEnd() {
		return end;
	}
	public void setEnd(RoutePosition end) {
		this.end = end;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Start: "+start);
		sb.append("\n");
		sb.append("End: "+end);
		sb.append("\n");
		return sb.toString();
	}
	
	
}
