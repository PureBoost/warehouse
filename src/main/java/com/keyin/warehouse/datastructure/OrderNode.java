package com.keyin.warehouse.datastructure;

import com.keyin.warehouse.model.Order;

public class OrderNode {

    Order data;
    OrderNode left;
    OrderNode right;

    public OrderNode(Order data) {
        this.data = data;
    }
}