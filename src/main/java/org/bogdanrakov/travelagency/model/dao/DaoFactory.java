package org.bogdanrakov.travelagency.model.dao;

import org.apache.log4j.Logger;

public abstract class DaoFactory {

    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    public abstract ClientDAO createClientDAO();

    public abstract ClientCredentialsDAO createClientCredentialsDAO();

    public abstract TourDAO createTourDAO();

    public abstract OrderDAO createOrderDAO();

    public static DaoFactory getInstance() {
        String className = Config.getInstance().getFactoryClassName();
        DaoFactory factory = null;
        try {
            factory = (DaoFactory) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.error("Error while initializing DAOFactory: ", e);
            throw new RuntimeException(e);
        }

        return factory;
    }
}
