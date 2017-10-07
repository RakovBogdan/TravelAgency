package org.bogdanrakov.travelagency.controller.command.admin;

import org.bogdanrakov.travelagency.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowAddTourCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/add_tour.jsp";
    }
}
