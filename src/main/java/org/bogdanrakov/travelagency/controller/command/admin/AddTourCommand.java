package org.bogdanrakov.travelagency.controller.command.admin;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.entity.TourType;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddTourCommand implements Command {

    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String destination = request.getParameter("destination");
        String description = request.getParameter("description");
        int duration = Integer.parseInt(request.getParameter("duration"));
        LocalDate start = LocalDate.parse(request.getParameter("start"));
        TourType type = TourType.valueOf(request.getParameter("type"));
        boolean hot = Boolean.parseBoolean(request.getParameter("hot"));
        int price = Integer.parseInt(request.getParameter("price"));
        boolean enabled = Boolean.valueOf(request.getParameter("enabled"));
        int discount = Integer.parseInt(request.getParameter("discount"));

        Tour tour = new Tour.Builder()
                .id(id)
                .title(title)
                .destination(destination)
                .description(description)
                .duration(duration)
                .start(start)
                .tourType(type)
                .hot(hot)
                .price(price)
                .enabled(enabled)
                .discount(discount)
                .build();

        if (tourService.changeTour(tour)) {
            return "/WEB-INF/jsp/account_admin.jsp";
        } else {
            return "nope";
        }
    }
}
