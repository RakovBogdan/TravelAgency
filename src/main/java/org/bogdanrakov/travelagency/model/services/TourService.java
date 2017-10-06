package org.bogdanrakov.travelagency.model.services;

import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.entity.TourType;

import java.util.List;
import java.util.Optional;

public interface TourService {

    int addTour(Tour tour);

    boolean changeTour(Tour tour);

    Optional<Tour> getTourById(long id);

    List<Tour> getHotToursFirst();

    List<Tour> getCheapToursFirst();

    List<Tour> getExpensiveToursFirst();

    List<Tour> getToursByType(TourType type);

    List<Tour> getToursWithDestination(String destination);


}
