package org.bogdanrakov.travelagency.model.services;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Order;
import org.bogdanrakov.travelagency.model.entity.Tour;

import java.util.List;

public interface OrderService {

    Order makeOrder(Client client, Tour tour, int toursAmount);

    List<Order> getClientOrders(Client client);

    int calculatePaymentForTour(Tour tour, Client client);

    public boolean payForOrder(long orderId);

    public boolean cancelOrder(Order order);
}
