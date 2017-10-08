package org.bogdanrakov.travelagency.model.dao.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.bogdanrakov.travelagency.model.dao.*;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private ComboPooledDataSource cpds = new ComboPooledDataSource();

    private static final Logger LOGGER = Logger.getLogger(JDBCDaoFactory.class);

    public JDBCDaoFactory() {
        Config config = Config.getInstance();
        try {
            cpds.setDriverClass(config.getDriverClassName());
        } catch (PropertyVetoException e) {
            LOGGER.error("Error while setting c3p0 driver class: ", e);
            throw new RuntimeException(e);
        }
        cpds.setJdbcUrl(config.getUrl());
        cpds.setUser(config.getUser());
        cpds.setPassword(config.getPassword());
        cpds.setMaxStatements(config.getMaxStatements());
    }

    Connection getConnection() {
        Connection connection;
        try {
            connection = cpds.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Error while obtaining connection: ", e);
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
