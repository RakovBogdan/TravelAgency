package org.bogdanrakov.travelagency.model.dao;

import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.entity.TourType;

import java.util.List;

public interface TourDAO extends GenericDAO<Tour> {

    List<Tour> getTourByType(TourType type);

    List<Tour> getToursWithDestination(String destination);

}
