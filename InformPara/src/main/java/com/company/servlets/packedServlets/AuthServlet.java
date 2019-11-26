package com.company.servlets.packedServlets;

import com.company.servlets.dto.UserDto;
import com.company.servlets.models.User;
import com.company.servlets.services.AuthService;
import lombok.Builder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthService authService = new AuthService();
        String email = req.getParameter("emailToCheck");
        String password = req.getParameter("passwordToCheck");
        if (email.trim().equals("") || password.trim().equals("")) {
            req.getSession().setAttribute("Error_data", "<label style=\"color: red;\">Email or password incorrect</label>");
            getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);
            req.getSession().removeAttribute("Error_data");
        } else {
            User user = User.builder().email(email).password(password).build();
//            ().setEmail(email).setPassword(password).build();
            User userOnline = authService.authoriseUserSetOnline(user);
            if (userOnline != null) {
                System.out.println("heelo");
                req.getSession().setAttribute("UserNameProfile", userOnline.getLogin());
                req.getSession().setAttribute("status", "online");
                resp.sendRedirect("/profile");
            } else {
                req.getSession().setAttribute("Error_data", "<label style=\"color: red;\">Email or password incorrect</label>");
                req.getSession().setAttribute("emailErrored", email);
                getServletContext().getRequestDispatcher(getServletContext().getContextPath() + "/hello.jsp").forward(req, resp);
                req.getSession().removeAttribute("Error_data");
                req.getSession().removeAttribute("emailErrored");
            }
        }
    }
}
