package org.bogdanrakov.travelagency.model.dao.impl;

import org.bogdanrakov.travelagency.model.dao.TourDAO;
import org.bogdanrakov.travelagency.model.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCTourDAO implements TourDAO {

    public static final String FIND_BY_TYPE = "SELECT * FROM `tour` WHERE `type`=?";

    public static final String FIND_WITH_DESTINATION = "SELECT * FROM `tour`" +
            " WHERE `destination` LIKE ?";

    public static final String FIND_ALL = "SELECT * FROM `tour`";

    public static final String FIND_BY_ID = "SELECT * FROM `tour` WHERE `tour_id` = ?";

    public static final String INSERT = "INSERT INTO `tour` (`title`, `destination`, " +
            "`description`, `duration`, `start`, `type`, `hot`, `price`, " +
            "`enabled`, `discount`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE = "UPDATE `tour` SET `title`=?, `destination`=?," +
            "`description`=?, `duration`=?, `start`=?, `type`=?, `hot`=?, `price`=?," +
            "`enabled`=?, `discount`=? " +
            "WHERE `tour_id`=?";

    public static final String DELETE = "DELETE FROM `tour` WHERE `tour_id`=?";

    private Connection connection;

    public JDBCTourDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Tour> getTourByType(TourType type) {
        List<Tour> result = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(FIND_BY_TYPE)) {
            query.setString(1, type.toString());
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Tour tour = getTourFromResultSet(rs);
                result.add(tour);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Tour> getToursWithDestination(String destination) {
        List<Tour> result = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(FIND_WITH_DESTINATION)) {
            query.setString(1, destination);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Tour tour = getTourFromResultSet(rs);
                result.add(tour);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Tour> findAll() {
        List<Tour> result = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Tour tour = getTourFromResultSet(rs);
                result.add(tour);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Tour> findById(long id) {
        Optional<Tour> result = Optional.empty();

        try (PreparedStatement query = connection.prepareStatement(FIND_BY_ID)) {
            query.setLong(1, id);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Tour tour = getTourFromResultSet(rs);
                result = Optional.of(tour);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int insert(Tour tour) {
        int result = -1;
        try (PreparedStatement query = connection.prepareStatement(INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, tour.getTitle());
            query.setString(2, tour.getDestination());
            query.setString(3, tour.getDescription());
            query.setInt(4, tour.getDuration());
            query.setDate(5, java.sql.Date.valueOf(tour.getStart()));
            query.setString(6, tour.getType().toString());
            query.setBoolean(7, tour.isHot());
            query.setInt(8, tour.getPrice());
            query.setBoolean(9, tour.isEnabled());
            query.setInt(10, tour.getDiscount());

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
    public boolean update(Tour tour) {
        boolean result = false;

        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, tour.getTitle());
            query.setString(2, tour.getDestination());
            query.setString(3, tour.getDescription());
            query.setInt(4, tour.getDuration());
            query.setDate(5, java.sql.Date.valueOf(tour.getStart()));
            query.setString(6, tour.getType().toString());
            query.setBoolean(7, tour.isHot());
            query.setInt(8, tour.getPrice());
            query.setBoolean(9, tour.isEnabled());
            query.setInt(10, tour.getDiscount());
            query.setLong(11, tour.getId());

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
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setLong(1, id);
            int queryResult = query.executeUpdate();
            if (queryResult != 0) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Tour getTourFromResultSet(ResultSet rs) throws SQLException {
        Tour tour = new Tour.Builder()
                .id(rs.getInt("tour_id"))
                .title(rs.getString("title"))
                .destination(rs.getString("destination"))
                .description(rs.getString("description"))
                .duration(rs.getInt("duration"))
                .start(rs.getDate("start").toLocalDate())
                .tourType(TourType.valueOf(rs.getString("type")))
                .hot(rs.getBoolean("hot"))
                .price(rs.getInt("price"))
                .enabled(rs.getBoolean("enabled"))
                .discount(rs.getInt("discount"))
                .build();
        return tour;
    }
}
