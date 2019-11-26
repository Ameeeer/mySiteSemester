package com.company.servlets.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;

//@WebFilter(filterName = "StatusFilter", urlPatterns = {"/","/registration"})
public class StatusFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute("status") == null) {
            req.getSession().setAttribute("status", "offline");
            System.out.println("soososoos");
            filterChain.doFilter(servletRequest,servletResponse);
        } else if ((req.getSession().getAttribute("status").equals("online"))) {
            System.out.println("onlineEDUSer");
            filterChain.doFilter(req, resp);
            ((HttpServletResponse) servletResponse).sendRedirect(req.getServletContext().getContextPath() + "/profile");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
