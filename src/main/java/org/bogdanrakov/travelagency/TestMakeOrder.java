package org.bogdanrakov.travelagency;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Order;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.services.*;

public class TestMakeOrder {

    public static void main(String[] args) {
        ClientService clientService = ClientServiceImpl.getInstance();
        Client client = clientService.getAllClients().get(0);
        System.out.println(client);

        TourService tourService = TourServiceImpl.getInstance();
        Tour tour = tourService.getExpensiveToursFirst().get(0);
        System.out.println(tour);

        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.makeOrder(client, tour, 1);
        System.out.println(order);
    }
}
