package com.company.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebFilter(filterName = "ProfileFilter(Status)", urlPatterns = {"/profile/*"})
public class StatusFilterOnProfile implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute("status").equals("offline")) {
            System.out.println("offlinedUser");
            filterChain.doFilter(req, resp);
            ((HttpServletResponse) servletResponse).sendRedirect("/hello");
        }
    }

    @Override
    public void destroy() {

    }
}
