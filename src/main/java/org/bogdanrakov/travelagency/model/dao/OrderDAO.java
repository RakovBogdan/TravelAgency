package org.bogdanrakov.travelagency.model.dao;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {

    List<Order> getClientOrders(Client client);

    boolean payOrder(long orderId);

    int getClientPayedOrdersCount(long clientId);
}
