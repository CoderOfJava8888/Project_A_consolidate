package main;

import com.ib.client.Contract;
import com.ib.client.EClient;
import com.ib.client.Order;

// https://groups.io/g/twsapi/message/40324
// instantiate this class in the nextValidId() callback. Then just call its
// placeOrder() method each time you want to place an order (from
// any thread). The orderId is returned by placeOrder().

public class OrderPlacer {

    private int orderId;
    private EClient client;
    
    
     
    public OrderPlacer(EClient client, int initialValue) {
        this.client = client;
        orderId = initialValue;
    }

    public synchronized int placeOrder(Contract contract, Order order) {
        int id = orderId++;
        client.placeOrder(id, contract, order);
        return id;
    }

}
