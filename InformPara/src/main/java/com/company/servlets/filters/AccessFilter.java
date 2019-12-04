package com.company.servlets.filters;

import com.company.servlets.services.AccessService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "AccessFilter", urlPatterns = {"/profile", "/profile/*"})
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        AccessService accessService = new AccessService();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Integer integer = (Integer) httpServletRequest.getSession().getAttribute("userIdProtected");
        List<String> roles = accessService.getAllUserRoles(integer);
        filterChain.doFilter(servletRequest, servletResponse);
        if (roles.contains("Admin") || roles.contains("Moderator") || roles.contains("Jesus")) {
            httpServletRequest.setAttribute("adminPanel", "");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.setAttribute("adminPanel", "disabled");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
