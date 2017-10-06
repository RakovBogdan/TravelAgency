package org.bogdanrakov.travelagency.model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String url;
    private String user;
    private String password;
    private String factoryClassName;

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
        } catch (IOException e) {
            e.printStackTrace();
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
}
