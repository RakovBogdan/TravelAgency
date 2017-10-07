package org.bogdanrakov.travelagency.controller.command;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.ClientCredentials;
import org.bogdanrakov.travelagency.model.services.ClientService;
import org.bogdanrakov.travelagency.model.services.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegisterCommand implements Command {

    ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        Client client = new Client.Builder()
                .name(request.getParameter("name"))
                .build();

        ClientCredentials credentials = new ClientCredentials.Builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        Optional<Client> clientObj = clientService.register(client, credentials);
        if(!clientObj.isPresent()) {
            return "/WEB-INF/jsp/register.jsp";
        } else {
            request.setAttribute("message", "Registration completed successfully");
            return "/WEB-INF/jsp/login.jsp";
        }
    }
}
