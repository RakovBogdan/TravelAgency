package org.bogdanrakov.travelagency.controller.command.admin;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.entity.TourType;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateTourCommand implements Command {

    private static final String PRICE_REGEX = "[0-9]{3,12}";
    private static final String DURATION_REGEX = "[0-9]{1,2}";
    private static final String DISCOUNT_REGEX = "[0-9]{3,4}";
    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        if (checkInputs(request)) {
            Tour tour = buildTour(request);
            if (tourService.changeTour(tour)) {
                request.setAttribute("tours", tourService.getHotToursFirst());
                return "/WEB-INF/jsp/account_admin.jsp";
            }
        }
        return "/WEB-INF/jsp/500.jsp";
    }

    private boolean checkInputs(HttpServletRequest request) {
        return checkPrice(request) &&
                checkDiscount(request) &&
                checkDuration(request) &&
                checkTitle(request) &&
                checkDestination(request) &&
                checkDescription(request);
    }

    private boolean checkPrice(HttpServletRequest request) {
        String price = request.getParameter("price");
        return price != null && price.matches(PRICE_REGEX);
    }

    private boolean checkDiscount(HttpServletRequest request) {
        String discount = request.getParameter("discount");
        return discount != null && discount.matches(DISCOUNT_REGEX);
    }

    private boolean checkDuration(HttpServletRequest request) {
        String duration = request.getParameter("duration");
        return duration != null && duration.matches(DURATION_REGEX);
    }

    private boolean checkTitle(HttpServletRequest request) {
        String title = request.getParameter("title");
        return title != null && !title.equals("");
    }

    private boolean checkDestination(HttpServletRequest request) {
        String destination = request.getParameter("destination");
        return destination != null && !destination.equals("");
    }

    private boolean checkDescription(HttpServletRequest request) {
        String description = request.getParameter("description");
        return description != null && description.matches(DURATION_REGEX);
    }

    private Tour buildTour(HttpServletRequest request) {
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

        return new Tour.Builder()
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
    }
}
