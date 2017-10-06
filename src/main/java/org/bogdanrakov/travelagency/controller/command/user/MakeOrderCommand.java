package org.bogdanrakov.travelagency.controller.command.user;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.services.*;

import javax.servlet.http.HttpServletRequest;

public class MakeOrderCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();
    private TourService tourService = TourServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        return null;
    }
}
