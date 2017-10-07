package org.bogdanrakov.travelagency.controller.command.user;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.services.OrderService;
import org.bogdanrakov.travelagency.model.services.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class PayOrderCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        long orderId = Long.parseLong(request.getParameter("orderId"));
        Client client = (Client) request
                .getSession().getAttribute("client");

        if (orderService.payForOrder(orderId, client)) {
            request.setAttribute("orders", orderService.getClientOrders(client));
            return "/WEB-INF/jsp/account.jsp";
        }

        return "/WEB-INF/jsp/500.jsp";
    }
}
