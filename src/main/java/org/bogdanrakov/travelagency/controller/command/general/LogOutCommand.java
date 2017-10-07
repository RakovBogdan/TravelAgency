package org.bogdanrakov.travelagency.controller.command.general;

import org.bogdanrakov.travelagency.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/WEB-INF/jsp/login.jsp";
    }
}
