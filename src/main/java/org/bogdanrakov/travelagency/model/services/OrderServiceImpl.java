package org.bogdanrakov.travelagency.model.services;

import org.bogdanrakov.travelagency.model.dao.DaoFactory;
import org.bogdanrakov.travelagency.model.dao.OrderDAO;
import org.bogdanrakov.travelagency.model.dao.TourDAO;
import org.bogdanrakov.travelagency.model.dao.impl.JDBCOrderDAO;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Order;
import org.bogdanrakov.travelagency.model.entity.OrderStatus;
import org.bogdanrakov.travelagency.model.entity.Tour;

import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final OrderService INSTANCE = new OrderServiceImpl();
    }

    public static OrderService getInstance() {
        return OrderServiceImpl.Holder.INSTANCE;
    }

    @Override
    public Order makeOrder(Client client, Tour tour, int toursAmount) {
        Order order = new Order.Builder()
                .client(client)
                .tour(tour)
                .date(LocalDateTime.now())
                .toursAmount(toursAmount)
                .status(OrderStatus.ORDERED)
                .build();

        OrderDAO orderDAO = daoFactory.createOrderDAO();
        orderDAO.insert(order);

        return order;
    }

    @Override
    public List<Order> getClientOrders(Client client) {
        OrderDAO orderDAO = daoFactory.createOrderDAO();

        return orderDAO.getClientOrders(client);
    }

    @Override
    public int payForOrder(Order order, Client client, Tour tour) {
        int result = -1;

        order.setClient(client);
        order.setTour(tour);
        OrderDAO orderDAO = daoFactory.createOrderDAO();
        order.setStatus(OrderStatus.PAYED);

        if (orderDAO.update(order)) {
            result = calculatePaymentForTour(tour, client) * order.getToursAmount();
        }

        return result;
    }

    @Override
    public int calculatePaymentForTour(Tour tour, Client client) {
        final double TO_PERCENTS_WITH_DOT = 100.;
        final double TO_ACTUAL_NUMBER = 100.;
        int tourPrice = tour.getPrice();

        double tourDiscount = (tourPrice * tour.getDiscount() / (TO_PERCENTS_WITH_DOT *
                TO_ACTUAL_NUMBER));
        double clientDiscount = (tourPrice * client.getDiscount() / (TO_PERCENTS_WITH_DOT *
                TO_ACTUAL_NUMBER));

        double amountToPay = (tourPrice - tourDiscount - clientDiscount );

        return (int)Math.round(amountToPay);
    }

    @Override
    public boolean cancelOrder(Order order) {
        OrderDAO orderDAO = daoFactory.createOrderDAO();
        order.setStatus(OrderStatus.CANCELED);

        return orderDAO.update(order);
    }
}
