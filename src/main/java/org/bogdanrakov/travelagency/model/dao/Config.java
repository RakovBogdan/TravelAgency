package org.bogdanrakov.travelagency.model.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String url;
    private String user;
    private String password;
    private String factoryClassName;
    private String driverClassName;
    private int maxStatements;

    private static final Logger LOGGER = Logger.getLogger(Config.class);

    public Config() {
        load();
    }

    private static class Holder {
        private static Config INSTANCE = new Config();
    }

    public static Config getInstance() {
        return Holder.INSTANCE;
    }

    private void load() {
        try(InputStream in = this.getClass().getResourceAsStream("/db.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            url = properties.getProperty("db.url");
            factoryClassName = properties.getProperty("db.factory.class");
            driverClassName = properties.getProperty("db.driver.class");
            maxStatements = Integer.parseInt(properties.getProperty("db.maxStatements"));
        } catch (IOException e) {
            LOGGER.error("Error while obtaining database configuration file: ", e);
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getFactoryClassName() {
        return factoryClassName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public int getMaxStatements() {
        return maxStatements;
    }
}
