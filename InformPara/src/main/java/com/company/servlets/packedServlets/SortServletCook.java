package com.company.servlets.packedServlets;

import com.company.servlets.ConnectionDB;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;
import com.sun.net.httpserver.HttpServer;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SortServletCook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("userListSorted", "");
        resp.addCookie(cookie);
        req.getRequestDispatcher("listUsers.jsp").forward(req,resp);
    }
}
