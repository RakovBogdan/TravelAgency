package org.bogdanrakov.travelagency.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ShowRegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/jsp/register.jsp";
    }
}
