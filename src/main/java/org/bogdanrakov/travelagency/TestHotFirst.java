package org.bogdanrakov.travelagency;

import org.bogdanrakov.travelagency.model.entity.TourType;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

public class TestHotFirst {

    public static void main(String[] args) {
        TourService tourService = TourServiceImpl.getInstance();
        System.out.println(tourService.getToursByType(TourType.VACATION));
        System.out.println();
        System.out.println(tourService.getToursWithDestination("paris"));
    }
}
