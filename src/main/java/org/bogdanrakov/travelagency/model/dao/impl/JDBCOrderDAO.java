package org.bogdanrakov.travelagency.model.dao.impl;

import org.apache.log4j.Logger;
import org.bogdanrakov.travelagency.model.dao.DaoFactory;
import org.bogdanrakov.travelagency.model.dao.OrderDAO;
import org.bogdanrakov.travelagency.model.dao.TourDAO;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Order;
import org.bogdanrakov.travelagency.model.entity.OrderStatus;
import org.bogdanrakov.travelagency.model.entity.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDAO implements OrderDAO {

    private static final String INSERT = "INSERT INTO `order` (`client_id`, `tour_id`, " +
            "`date`, `toursAmount`, `status`, `payment`) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String CLIENT_ORDERS = "SELECT * FROM `order` " +
            "WHERE `client_id`=?";

    private static final String CLIENT_PAYED_ORDERS = "SELECT count(`order_id`) " +
            "FROM `order` WHERE `client_id`= ? " +
            "AND `order`.`status`='PAYED'";

    private static final String UPDATE = "UPDATE `order` SET `client_id`=?, " +
            "`tour_id`=?, `date`=?, `toursAmount`=?, `status`=?, `payment`=? " +
            "WHERE `order_id`=?";

    private static final String PAY_ORDER = "UPDATE `order` SET `status`=? WHERE `order_id`=?";

    private static final String FIND_ALL = "SELECT * FROM `order`";

    private static final String FIND_BY_ID = "SELECT * FROM `order` WHERE `order_id`=?";

    private static final String DELETE = "DELETE FROM `order` WHERE `order_id`=?";

    private Connection connection;

    private static final Logger LOGGER = Logger.getLogger(JDBCOrderDAO.class);

    JDBCOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getClientPayedOrdersCount(long clientId) {
        int result = -1;

        try (PreparedStatement query = connection.prepareStatement(CLIENT_PAYED_ORDERS)) {
            query.setLong(1, clientId);
            ResultSet rs = query.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while getting payed orders count: ", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean payOrder(long orderId) {
        boolean result = false;

        try (PreparedStatement query = connection.prepareStatement(PAY_ORDER)) {
            query.setString(1, OrderStatus.PAYED.toString());
            query.setLong(2, orderId);
            int queryResult = query.executeUpdate();
            if (queryResult != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Error while paying order: ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Order> getClientOrders(Client client) {
        List<Order> orders = new ArrayList<>();

        DaoFactory daoFactory = DaoFactory.getInstance();
        TourDAO tourDAO = daoFactory.createTourDAO();

        try (PreparedStatement query = connection.prepareStatement(CLIENT_ORDERS)) {
            query.setLong(1, client.getId());
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Tour tour = tourDAO.findById(
                        Long.parseLong(rs.getString("tour_id"))).get();
                Order order = getOrderFromResultSet(rs);
                order.setTour(tour);
                orders.add(order);
            }

        } catch (SQLException e) {
            LOGGER.error("Error while getting client orders: ", e);
            throw new RuntimeException(e);
        }

        return orders;
    }

    @Override
    public int insert(Order order) {
        int result = -1;

        try (PreparedStatement query = connection.prepareStatement(INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, order.getClient().getId());
            query.setLong(2, order.getTour().getId());
            query.setTimestamp(3, Timestamp.valueOf(order.getDate()));
            query.setInt(4, order.getToursAmount());
            query.setString(5, order.getStatus().toString());
            query.setInt(6, order.getPayment());
            query.executeUpdate();

            ResultSet rsId = query.getGeneratedKeys();
            if (rsId.next()) {
                result = rsId.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while inserting order: ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                result.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while getting all orders: ", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Optional<Order> findById(long id) {
        Optional<Order> result = Optional.empty();

        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setLong(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Order order = getOrderFromResultSet(rs);
                result = Optional.of(order);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while getting order by id: ", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean update(Order order) {
        boolean result = false;

        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setLong(1, order.getClient().getId());
            query.setLong(2, order.getTour().getId());
            query.setTimestamp(3, Timestamp.valueOf(order.getDate()));
            query.setInt(4, order.getToursAmount());
            query.setString(5, order.getStatus().toString());
            query.setInt(6, order.getPayment());


            int queryResult = query.executeUpdate();
            if (queryResult != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Error while updating order: ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setLong(1, id);
            int queryResult = query.executeUpdate();
            if (queryResult != 0) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Error while deleting order: ", e);
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error while closing connection: ", e);
            throw new RuntimeException(e);
        }
    }

    private Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order.Builder()
                .id(Long.parseLong(rs.getString("order_id")))
                .date(rs.getTimestamp("date").toLocalDateTime())
                .toursAmount(rs.getInt("toursAmount"))
                .status(OrderStatus.valueOf(rs.getString("status")))
                .payment(rs.getInt("payment"))
                .build();

        return order;
    }
}
