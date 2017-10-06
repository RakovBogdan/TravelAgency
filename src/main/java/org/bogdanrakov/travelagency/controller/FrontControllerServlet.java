package org.bogdanrakov.travelagency.controller;

import org.bogdanrakov.travelagency.controller.command.Command;
import org.bogdanrakov.travelagency.controller.command.LoginCommand;
import org.bogdanrakov.travelagency.controller.command.ShowMainCommand;
import org.bogdanrakov.travelagency.controller.command.admin.ShowTourDetails;
import org.bogdanrakov.travelagency.controller.command.admin.UpdateTourCommand;

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
