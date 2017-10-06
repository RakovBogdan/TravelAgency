package org.bogdanrakov.travelagency.model.dao.impl;

import org.bogdanrakov.travelagency.model.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    Connection getConnection() {
        Config config = Config.getInstance();
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    config.getUrl(), config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    @Override
    public ClientDAO createClientDAO() {
        return new JDBCClientDAO(getConnection());
    }

    @Override
    public ClientCredentialsDAO createClientCredentialsDAO() {
        return new JDBCClientCredentialsDAO(getConnection());
    }

    @Override
    public TourDAO createTourDAO() {
        return new JDBCTourDAO(getConnection());
    }

    @Override
    public OrderDAO createOrderDAO() {
        return new JDBCOrderDAO(getConnection());
    }
}
