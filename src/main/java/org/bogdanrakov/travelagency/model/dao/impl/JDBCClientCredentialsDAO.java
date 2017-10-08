package org.bogdanrakov.travelagency.model.dao.impl;

import org.apache.log4j.Logger;
import org.bogdanrakov.travelagency.model.dao.ClientCredentialsDAO;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.ClientCredentials;
import org.bogdanrakov.travelagency.model.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCClientCredentialsDAO implements ClientCredentialsDAO {

    private static final String IS_LOGIN_OCCUPIED = "SELECT * FROM `client_login`" +
            "WHERE `email`=?";

    private static final String FIND_ALL = "SELECT * FROM `client_login`";

    private static final String FIND_BY_ID = "SELECT * FROM `client_login` " +
            "WHERE `client_id` = ?";

    private static final String INSERT = "INSERT INTO `client_login` (" +
            "`client_id`, `email`, `password`, `role`, `enabled`) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE `client_login` " +
            "SET `email`=?, `password`=?, `role`= ?, `enabled`=? WHERE `client_id`=?";

    private static final String DELETE = "DELETE from `client_login` WHERE `client_id`=?";

    private static final Logger LOGGER = Logger.getLogger(JDBCClientCredentialsDAO.class);

    private Connection connection;

    JDBCClientCredentialsDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean isLoginOccupied(String login) {
        boolean result = false;

        try (PreparedStatement query = connection.prepareStatement(IS_LOGIN_OCCUPIED)) {
            query.setString(1, login);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Error while checking if login is occupied: ", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public Optional<ClientCredentials> findById(long id) {
        Optional<ClientCredentials> result = Optional.empty();

        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setLong(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                ClientCredentials credentials = getCredentialsFromResultSet(rs);
                result = Optional.of(credentials);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while finding client credentials: ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<ClientCredentials> findAll() {
        List<ClientCredentials> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = query.executeQuery();
            while (rs.next()) {
                ClientCredentials credentials = getCredentialsFromResultSet(rs);
                result.add(credentials);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while finding all cient credentials: ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int insert(ClientCredentials credentials) {
        int result = -1;

        try (PreparedStatement query = connection.prepareStatement(INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, credentials.getClient().getId());
            query.setString(2, credentials.getEmail());
            query.setString(3, credentials.getPassword());
            query.setString(4, credentials.getRole().toString());
            query.setBoolean(5, credentials.isEnabled());
            query.executeUpdate();
            ResultSet rsId = query.getGeneratedKeys();
            if (rsId.next()) {
                result = rsId.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error while inserting credentials: ", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public boolean update(ClientCredentials clientCredentials) {
        boolean result = false;
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, clientCredentials.getEmail());
            query.setString(2, clientCredentials.getPassword());
            query.setString(3, clientCredentials.getRole().toString());
            query.setBoolean(4, clientCredentials.isEnabled());
            query.setLong(5, clientCredentials.getClient().getId());

            int queryResult = query.executeUpdate();
            if (queryResult != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error("Error while updating credentials: ", e);
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
            LOGGER.error("Error while deleting credentials: ", e);
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error while closing connection: ", e);
            throw new RuntimeException(e);
        }
    }

    private ClientCredentials getCredentialsFromResultSet(ResultSet rs) throws SQLException {
        ClientCredentials credentials = new ClientCredentials.Builder()
                .id(rs.getLong("client_id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(Role.valueOf(rs.getString("role")))
                .enabled(rs.getBoolean("enabled"))
                .build();

        return credentials;
    }
}
