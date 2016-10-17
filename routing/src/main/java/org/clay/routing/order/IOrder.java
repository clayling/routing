package org.clay.routing.order;

import org.clay.routing.struct.Item;

public interface IOrder {

	int orderNumber();
	
	Item getItem(int i);
}
