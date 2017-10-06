package org.bogdanrakov.travelagency;

import org.bogdanrakov.travelagency.model.dao.DaoFactory;
import org.bogdanrakov.travelagency.model.dao.TourDAO;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

public class TestChangeTour {

    public static void main(String[] args) {
        TourService tourService = new TourServiceImpl();
        DaoFactory factory = DaoFactory.getInstance();
        Tour tour;

        try (TourDAO tourDAO = factory.createTourDAO()) {
            tour = tourDAO.findById(1).get();
            tour.setDestination("Ukraine");
            tourService.changeTour(tour);
            System.out.println(tourDAO.findById(1).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
