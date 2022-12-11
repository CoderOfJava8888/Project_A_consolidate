/* Copyright (C) 2019 Interactive Brokers LLC. All rights reserved. This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */
package main;

import java.util.ArrayList;
import java.util.List;
import com.ib.client.VolumeCondition;
import com.ib.client.TimeCondition;
import com.ib.client.PercentChangeCondition;
import com.ib.client.MarginCondition;
import com.ib.client.ExecutionCondition;
import com.ib.client.PriceCondition;
import com.ib.client.Order;
import com.ib.client.OrderComboLeg;
import com.ib.client.OrderCondition;
import com.ib.client.OrderConditionType;
import com.ib.client.OrderType;
import com.ib.client.TagValue;

//  All orders must be market.


public class OrderTypes {

    public static Order Market(String action, double quantity) {
        //! [market]
        Order order = new Order();
        order.action(action);
        order.orderType("MKT");
        order.tif("GTC");
        order.totalQuantity(quantity);
        //! [market]
        return order;
    }

    
    
    

    public static Order MarketOrder(String action, double quantity) {
        //! [market]
        Order order = new Order();
        order.action(action);
        order.orderType("MKT");
        order.totalQuantity(quantity);
        //! [market]
        return order;
    }

    public static Order TrailingStop(String action, double quantity) {
        //! [market]
        Order order = new Order();
        order.action(action);
        order.orderType("TRAIL");
        order.totalQuantity(quantity);
        order.trailingPercent(0.02);
        order.trailStopPrice(100.00);
        //! [market]
        return order;
    }

//https://interactivebrokers.github.io/tws-api/basic_orders.html
    
    
}
