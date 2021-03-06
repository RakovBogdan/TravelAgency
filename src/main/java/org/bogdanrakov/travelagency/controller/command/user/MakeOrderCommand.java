package org.bogdanrakov.travelagency.controller.command.user;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Tour;
import org.bogdanrakov.travelagency.model.services.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MakeOrderCommand implements Command {

    private static final String TOURS_AMOUNT_REGEX = "[0-9]{1,2}";

    private OrderService orderService = OrderServiceImpl.getInstance();
    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Client client = (Client) request
                .getSession().getAttribute("client");
        Optional<Tour> tour = tourService.getTourById(
                Long.parseLong(request.getParameter("tourId")));
        if (tour.isPresent() && checkToursAmount(request)) {
            int toursAmount = Integer.parseInt(request.getParameter("toursAmount"));
            orderService.makeOrder(client, tour.get(), toursAmount);
            request.setAttribute("orders", orderService.getClientOrders(client));
            return "/WEB-INF/jsp/account.jsp";
        }
        return "/WEB-INF/jsp/500.jsp";
    }

    private boolean checkToursAmount(HttpServletRequest request) {
        String toursAmount = request.getParameter("toursAmount");
        return toursAmount.matches(TOURS_AMOUNT_REGEX);
    }
}