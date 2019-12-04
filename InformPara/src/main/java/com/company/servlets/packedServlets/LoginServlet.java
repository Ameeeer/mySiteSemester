package com.company.servlets.packedServlets;

import com.company.servlets.ConnectionDB;
import com.company.servlets.models.User;
import com.company.servlets.services.AuthService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "Auth", urlPatterns = {"/", "/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setMaxInactiveInterval(60 * 60 * 24);
//        if (req.getSession().getAttribute("status") == null) {
//            req.getSession().setAttribute("status", "offline");
//        } else {
//            req.getSession().setAttribute("hide", "");
//            req.getSession().setAttribute("browser", "");
//        }
        req.getRequestDispatcher("/hello.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthService authService = new AuthService();
        String email = req.getParameter("emailToCheck");
        String password = req.getParameter("passwordToCheck");
        if (email.trim().equals("") || password.trim().equals("") ) {
            req.getSession().setAttribute("Error_data", "<label style=\"color: red;\">Email or password incorrect</label>");
            getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);
            req.getSession().removeAttribute("Error_data");
        } else {
//            ().setEmail(email).setPassword(password).build();
            User userOnline = authService.authoriseUserSetOnline(User.builder().email(email).password(password).build());
            if (userOnline != null) {
                req.getSession().setAttribute("UserNameProfile", userOnline.getLogin());
                req.getSession().setAttribute("userIdProtected",userOnline.getId());
                req.getSession().setAttribute("status", "online");
//                req.getSession().setAttribute("role", userOnline.getRole());
                resp.sendRedirect(req.getContextPath() + "/profile");
            } else {
                req.getSession().setAttribute("Error_data", "<label style=\"color: red;\">Email or password incorrect</label>");
                req.getSession().setAttribute("emailErrored", email);
                getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);
                req.getSession().removeAttribute("Error_data");
                req.getSession().removeAttribute("emailErrored");
            }
        }
    }
}

