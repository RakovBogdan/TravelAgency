package org.bogdanrakov.travelagency.controller.command;

import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ShowMainCommand implements Command {

    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("tours", tourService.getHotToursFirst());
        return "/WEB-INF/jsp/main.jsp";
    }
}
