package org.bogdanrakov.travelagency.model.dao.impl;

import org.bogdanrakov.travelagency.model.dao.ClientDAO;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.ClientCredentials;
import org.bogdanrakov.travelagency.model.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCClientDAO implements ClientDAO {

    public static final String FIND_ALL = "SELECT * FROM `client` JOIN `client_login`" +
            "ON client.client_id = client_login.client_id";

    public static final String FIND_BY_LOGIN_PASSWORD = "SELECT * FROM `client` JOIN `client_login`" +
            "ON client.client_id = client_login.client_id " +
            "WHERE `client_login`.`email` = ? AND `client_login`.`password` = ?";

    public static final String FIND_BY_ID = "SELECT * FROM `client` WHERE `client_id` = ?";

    public static final String INSERT = "INSERT INTO `client` (`name`, `discount`) " +
            "VALUES (?, ?)";

    public static final String UPDATE = "UPDATE `client` SET `name`=?, `discount`=? " +
            "WHERE `client_id`=?";

    public static final String DELETE = "DELETE from `client` WHERE `client_id`=?";

    private Connection connection;

    public JDBCClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Client> findByLoginAndPassword(String login, String password) {
        Optional<Client> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD)) {
            query.setString(1, login);
            query.setString(2, password);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {

                ClientCredentials credentials = new ClientCredentials.Builder()
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .role(Role.valueOf(rs.getString("role")))
                        .enabled(rs.getBoolean("enabled")).
                                build();

                Client client = getClientFromResultSet(rs);

                client.setClientCredentials(credentials);
                credentials.setClient(client);

                result = Optional.of(client);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Client> findById(long id) {
        Optional<Client> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setLong(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Client client = getClientFromResultSet(rs);
                result = Optional.of(client);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Client> findAll() {
        List<Client> result = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                ClientCredentials credentials = new ClientCredentials.Builder()
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .role(Role.valueOf(rs.getString("role")))
                        .enabled(rs.getBoolean("enabled")).
                        build();

                Client client = getClientFromResultSet(rs);

                client.setClientCredentials(credentials);
                credentials.setClient(client);
                result.add(client);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int insert(Client client) {
        int result = -1;
        try (PreparedStatement query = connection.prepareStatement(INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, client.getName());
            query.setInt(2, client.getDiscount());
            query.executeUpdate();
            ResultSet rsId = query.getGeneratedKeys();
            if (rsId.next()) {
                result = rsId.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean update(Client client) {
        boolean result = false;
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, client.getName());
            query.setInt(2, client.getDiscount());
            query.setLong(3, client.getId());
            int queryResult = query.executeUpdate();
            if (queryResult != 0) {
                result = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setLong(1, id);
            int queryResult = query.executeUpdate();
            if (queryResult != 0) {
                result = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client.Builder()
                .id(rs.getInt("client_id"))
                .name(rs.getString("name"))
                .discount(rs.getInt("discount"))
                .build();

        return client;
    }
}
