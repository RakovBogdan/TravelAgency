package org.bogdanrakov.travelagency.controller.command.admin;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowTourDetails implements Command {

    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        long tourId = Long.parseLong(request.getParameter("tourId"));

        Optional<Tour> tour = tourService.getTourById(tourId);
        if (tour.isPresent()) {
            request.setAttribute("tour", tour.get());
            return "/WEB-INF/jsp/edit_tour.jsp";
        } else {
            return "/WEB-INF/jsp/500.jsp";
        }
    }
}
