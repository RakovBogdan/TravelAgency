package org.bogdanrakov.travelagency.controller;

import org.bogdanrakov.travelagency.controller.command.*;
import org.bogdanrakov.travelagency.controller.command.admin.AddTourCommand;
import org.bogdanrakov.travelagency.controller.command.admin.ShowAddTourCommand;
import org.bogdanrakov.travelagency.controller.command.general.*;
import org.bogdanrakov.travelagency.controller.command.user.MakeOrderCommand;
import org.bogdanrakov.travelagency.controller.command.admin.ShowTourDetails;
import org.bogdanrakov.travelagency.controller.command.admin.UpdateTourCommand;
import org.bogdanrakov.travelagency.controller.command.user.OrderApprovalCommand;
import org.bogdanrakov.travelagency.controller.command.user.PayOrderCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FrontControllerServlet extends HttpServlet {

    private Map<String, Command> allCommands;
//    private static final Logger LOGGER = Logger.getRootLogger();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        allCommands = new HashMap<>();
        allCommands.put("login", new LoginCommand());
        allCommands.put("tourDetails", new ShowTourDetails());
        allCommands.put("confirmEdit", new UpdateTourCommand());
        allCommands.put("showMain", new ShowMainCommand());
        allCommands.put("orderApproval", new OrderApprovalCommand());
        allCommands.put("makeOrder", new MakeOrderCommand());
        allCommands.put("showMyAccount", new ShowMyAccountCommand());
        allCommands.put("showRegister", new ShowRegistrationCommand());
        allCommands.put("register", new RegisterCommand());
        allCommands.put("payOrder", new PayOrderCommand());
        allCommands.put("showByTourType", new ShowByTourTypeCommand());
        allCommands.put("showAddTour", new ShowAddTourCommand());
        allCommands.put("addTour", new AddTourCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessing(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessing(req, resp);
    }

    private void doProcessing(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = allCommands.get(req.getParameter("command"));
        String page = command.execute(req);

        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/500.jsp").forward(req, resp);
        }
    }
}
