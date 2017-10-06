package org.bogdanrakov.travelagency;

import org.bogdanrakov.travelagency.model.dao.DaoFactory;
import org.bogdanrakov.travelagency.model.dao.TourDAO;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import java.time.LocalDate;

public class TestInsertTour {

    public static void main(String[] args) {
        DaoFactory factory = DaoFactory.getInstance();
        try (TourDAO tourDAO = factory.createTourDAO()) {
            Tour tour = tourDAO.findById(1).get();
            System.out.println(tour);
            tour.setStart(LocalDate.of(2108, 10, 2));
            TourService tourService = new TourServiceImpl();
            tourService.addTour(tour);
            tour = tourDAO.findById(5).get();
            System.out.println(tour);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
