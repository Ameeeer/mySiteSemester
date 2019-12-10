package com.company.servlets.filters;

import com.company.servlets.services.AccessService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "ProfileFilter(Status)", urlPatterns = {"/profile/*", "/profile", "/players"})
public class StatusFilterOnProfile implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        AccessService accessService = new AccessService();
        if (httpServletRequest.getSession().getAttribute("userRights") == null || httpServletRequest.getSession().getAttribute("status") == null || httpServletRequest.getSession().getAttribute("status").equals("") || httpServletRequest.getSession().getAttribute("status").equals("offline")) {
            httpServletResponse.sendRedirect("/hello");
        } else if (httpServletRequest.getSession().getAttribute("userRights").equals("not issued")) {
            httpServletRequest.getSession().setAttribute("userRights", "issued");
            Integer integer = (Integer) httpServletRequest.getSession().getAttribute("userIdProtected");
            List<String> roles = accessService.getAllUserRoles(integer);
            if (roles.contains("Admin") || roles.contains("Moderator") || roles.contains("Jesus")) {
                httpServletRequest.getSession().setAttribute("adminPanel", "");
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                ((HttpServletRequest) servletRequest).getSession().setAttribute("adminPanel", "hidden");
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
