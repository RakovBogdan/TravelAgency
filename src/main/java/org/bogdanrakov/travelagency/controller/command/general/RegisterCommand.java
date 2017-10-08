package org.bogdanrakov.travelagency.controller.command.general;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.ClientCredentials;
import org.bogdanrakov.travelagency.model.services.ClientService;
import org.bogdanrakov.travelagency.model.services.ClientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegisterCommand implements Command {

    private static final String PASSWORD_REGEX = "^[A-Za-zА-Яа-яІЇЄіїє0-9~!@#$%^&*()-_=+/|.]{3,20}$";
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
            "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        if (checkInputs(request)) {
            Client client = buildClient(request);
            ClientCredentials credentials = buildCredentials(request);
            Optional<Client> clientObj = clientService.register(client, credentials);
            if (clientObj.isPresent()) {
                request.setAttribute("message", "Registration completed successfully");
                return "/WEB-INF/jsp/login.jsp";
            }
        }

        request.setAttribute("message", "Registration failed");
        return "/WEB-INF/jsp/register.jsp";
    }

    private boolean checkInputs(HttpServletRequest request) {
        return checkPassword(request) &&
                checkEmail(request);
    }

    private boolean checkPassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        return password != null && password.matches(PASSWORD_REGEX);
    }

    private boolean checkEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        return email != null && email.matches(EMAIL_REGEX);
    }

    private Client buildClient(HttpServletRequest request) {
        return new Client.Builder()
                .name(request.getParameter("name"))
                .build();
    }

    private ClientCredentials buildCredentials(HttpServletRequest request) {
        return new ClientCredentials.Builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
    }
}
