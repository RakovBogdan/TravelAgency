package org.bogdanrakov.travelagency.controller.command.general;

import org.bogdanrakov.travelagency.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowRegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/register.jsp";
    }
}
