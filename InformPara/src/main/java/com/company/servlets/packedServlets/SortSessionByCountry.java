package com.company.servlets.packedServlets;

import com.company.servlets.ConnectionDB;
import com.company.servlets.models.User;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;
import com.company.servlets.services.UserRepoService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SortSessionByCountry extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepoService userRepoService = new UserRepoService();
        List<User> users = userRepoService.getUsrsOrderedBCountry();
        String[] logins = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            logins[i] = users.get(i).getLogin();
        }
        req.getSession().setAttribute("userListing", logins);
        resp.sendRedirect("/sortListServlet");
    }
}
