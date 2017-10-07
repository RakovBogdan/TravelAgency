package org.bogdanrakov.travelagency.controller.command.general;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.TourType;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ShowByTourTypeCommand implements Command {

    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        TourType type = TourType.valueOf(request.getParameter("type"));

        request.setAttribute("type", type);
        request.setAttribute("tours", tourService.getToursByType(type));
        return "/WEB-INF/jsp/main.jsp";
    }
}
