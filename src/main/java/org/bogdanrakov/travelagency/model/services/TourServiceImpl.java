package org.bogdanrakov.travelagency.model.services;

import org.bogdanrakov.travelagency.model.dao.DaoFactory;
import org.bogdanrakov.travelagency.model.dao.TourDAO;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.entity.TourType;

import java.util.*;

public class TourServiceImpl implements TourService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final TourService INSTANCE = new TourServiceImpl();
    }

    public static TourService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<Tour> getTourById(long id) {
        TourDAO tourDAO = daoFactory.createTourDAO();

        return tourDAO.findById(id);
    }

    @Override
    public int addTour(Tour tour) {
        TourDAO tourDAO = daoFactory.createTourDAO();

        return tourDAO.insert(tour);
    }

    @Override
    public boolean changeTour(Tour tour) {
        TourDAO tourDAO = daoFactory.createTourDAO();

        return tourDAO.update(tour);
    }

    @Override
    public List<Tour> getHotToursFirst() {
        TourDAO tourDAO = daoFactory.createTourDAO();

        List<Tour> result = tourDAO.findAll();
        Collections.sort(result, new Comparator<Tour>() {
            @Override
            public int compare(Tour o1, Tour o2) {
                    return Boolean.compare(o2.isHot(), o1.isHot());
                }
        });

        return result;
    }

    @Override
    public List<Tour> getCheapToursFirst() {
        TourDAO tourDAO = daoFactory.createTourDAO();

        final int sign = 1;
        List<Tour> result = tourDAO.findAll();
        Collections.sort(result, new Comparator<Tour>() {
            @Override
            public int compare(Tour o1, Tour o2) {
                return sign * Integer.compare(o1.getPrice(), o2.getPrice());
            }
        });

        return result;
    }

    @Override
    public List<Tour> getExpensiveToursFirst() {
        TourDAO tourDAO = daoFactory.createTourDAO();

        final int sign = -1;
        List<Tour> result = tourDAO.findAll();
        Collections.sort(result, new Comparator<Tour>() {
            @Override
            public int compare(Tour o1, Tour o2) {
                return sign * Integer.compare(o1.getPrice(), o2.getPrice());
            }
        });

        return result;
    }

    @Override
    public List<Tour> getToursByType(TourType type) {
        TourDAO tourDAO = daoFactory.createTourDAO();

        return tourDAO.getTourByType(type);
    }

    @Override
    public List<Tour> getToursWithDestination(String destination) {
        TourDAO tourDAO = daoFactory.createTourDAO();

        return tourDAO.getToursWithDestination(destination);
    }
}
