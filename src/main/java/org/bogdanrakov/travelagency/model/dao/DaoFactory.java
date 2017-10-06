package org.bogdanrakov.travelagency.model.dao;

public abstract class DaoFactory {

    public abstract ClientDAO createClientDAO();

    public abstract ClientCredentialsDAO createClientCredentialsDAO();

    public abstract TourDAO createTourDAO();

    public abstract OrderDAO createOrderDAO();

    public static DaoFactory getInstance() {
        String className = Config.getInstance().getFactoryClassName();
        DaoFactory factory = null;
        try {
            factory = (DaoFactory) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }
}
