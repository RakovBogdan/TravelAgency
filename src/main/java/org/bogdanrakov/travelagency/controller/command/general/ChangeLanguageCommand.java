package org.bogdanrakov.travelagency.controller.command.general;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements Command {

    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter("language");
        request.getSession().setAttribute("language", language);

        request.setAttribute("tours", tourService.getHotToursFirst());
        return "/WEB-INF/jsp/main.jsp";
    }
}
