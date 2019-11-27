package com.company.servlets.packedServlets;

import com.company.servlets.models.User;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;
import com.company.servlets.services.AuthCheck;
import com.company.servlets.services.RegistrateService;
import com.company.servlets.services.UserRepoService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/registration")
public class RegistationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("Email");
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        String country = req.getParameter("Country");
        String userInfo = req.getParameter("Info");
        String role = req.getParameter("role");
        List<String> list = new ArrayList<>();
        list.add(email);
        list.add(login);
        list.add(password);
        list.add(country);
        list.add(userInfo);
        list.add(role);
        RegistrateService registrateService = new RegistrateService();
        System.out.println(registrateService.checkFields(list));
        if (!registrateService.checkFields(list)){
            req.getServletContext().setAttribute("errEmail",email);
            req.getServletContext().setAttribute("errLogin",login);
            req.getServletContext().setAttribute("errInfo",userInfo);
            req.getSession().setAttribute("hide", "hide");
            req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
            req.getServletContext().removeAttribute("errEmail");
            req.getServletContext().removeAttribute("errLogin");
            req.getServletContext().removeAttribute("errInfo");
        }
        User newuser = User.builder()
                .email(email).login(login).password(password).country(country).info(userInfo).role(role).build();
        AuthCheck authCheck = new AuthCheck();
        if (authCheck.checkEmail(newuser)) {
            if (registrateService.registrateUser(newuser)) {
                resp.sendRedirect("/hello");
            } else {
                req.getServletContext().setAttribute("errEmail",email);
                req.getServletContext().setAttribute("errLogin",login);
                req.getServletContext().setAttribute("errInfo",userInfo);
                req.getSession().setAttribute("hide", "hide");
                req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
                req.getServletContext().removeAttribute("errEmail");
                req.getServletContext().removeAttribute("errLogin");
                req.getServletContext().removeAttribute("errInfo");
            }
        } else {
            req.getServletContext().setAttribute("errEmail",email);
            req.getServletContext().setAttribute("errLogin",login);
            req.getServletContext().setAttribute("errInfo",userInfo);
            req.getSession().setAttribute("hide", "hide");
            req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
            req.getServletContext().removeAttribute("errEmail");
            req.getServletContext().removeAttribute("errLogin");
            req.getServletContext().removeAttribute("errInfo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        req.getSession().setAttribute("hide", "");
        try {
            System.out.println("reggpage");
            req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println("err");
            e.printStackTrace();
        }
    }
}

