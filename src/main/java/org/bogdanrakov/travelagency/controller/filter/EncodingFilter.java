package org.bogdanrakov.travelagency.controller.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException,  UnsupportedEncodingException {
        String encoding = request.getCharacterEncoding();
        if (!"UTF-8".equalsIgnoreCase(encoding)) {
            request.setCharacterEncoding("UTF-8");
        }
        try {
            chain.doFilter(request, response);
        } catch (IOException e) {
            throw new UnsupportedEncodingException(e.getMessage());
        }
    }
}
