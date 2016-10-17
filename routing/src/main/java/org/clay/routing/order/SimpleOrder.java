package org.clay.routing.order;

import java.util.List;

import org.clay.routing.struct.Item;

public class SimpleOrder implements IOrder {
		
	private List<Item> items;
	
	public int orderNumber() {
		return items.size();
	}

	public Item getItem(int i) {
		return items.get(i);

	}
	
	public void addItem(Item item){
		items.add(item);
	}

	
}
