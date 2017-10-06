package org.bogdanrakov.travelagency.controller.command;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Role;
import org.bogdanrakov.travelagency.model.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LoginCommand implements Command {

    private ClientService clientService = ClientServiceImpl.getInstance();
    private TourService tourService = TourServiceImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Client> client = clientService.login(login, password);

        if(client.isPresent() && client.get().getClientCredentials().isEnabled()) {
            Client clientObj = client.get();

            HttpSession session = request.getSession();
            session.setAttribute("client", clientObj);

            if (clientObj.getClientCredentials().getRole().equals(Role.ADMIN)) {
                request.setAttribute("tours", tourService.getHotToursFirst());
                return "/WEB-INF/jsp/account_admin.jsp";
            } else {
                request.setAttribute("orders", orderService.getClientOrders(clientObj));
                return "/WEB-INF/jsp/account.jsp";
            }

        } else {
            return "/WEB-INF/jsp/login.jsp";
        }

    }
}
