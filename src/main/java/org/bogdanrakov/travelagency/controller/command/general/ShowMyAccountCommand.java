package org.bogdanrakov.travelagency.controller.command.general;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Role;
import org.bogdanrakov.travelagency.model.services.OrderService;
import org.bogdanrakov.travelagency.model.services.OrderServiceImpl;
import org.bogdanrakov.travelagency.model.services.TourService;
import org.bogdanrakov.travelagency.model.services.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ShowMyAccountCommand implements Command {

    private TourService tourService = TourServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("client");

        if (client == null) {
            return "/WEB-INF/jsp/login.jsp";
        }

        if (client.getClientCredentials().getRole().equals(Role.ADMIN)) {
            request.setAttribute("tours", tourService.getHotToursFirst());
            return "/WEB-INF/jsp/account_admin.jsp";
        } else {
            request.setAttribute("orders", orderService.getClientOrders(client));
            return "/WEB-INF/jsp/account.jsp";
        }
    }
}
