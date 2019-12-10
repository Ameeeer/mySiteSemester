package com.company.servlets.packedServlets;

import com.company.servlets.dto.UserDto;
import com.company.servlets.models.User;
import com.company.servlets.services.AuthCheck;
import com.company.servlets.services.UserProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserProfileServl", urlPatterns = {"/profile/user", "/profile/update"})
public class UserProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserProfileService profileService = new UserProfileService();
        int id = (int) req.getSession().getAttribute("userIdProtected");
        UserDto dto = profileService.getUser(id);
        req.getSession().setAttribute("UserDto", dto);
        req.getRequestDispatcher("/userProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserProfileService userProfileService = new UserProfileService();
        AuthCheck authCheck = new AuthCheck();
        UserDto userDto = UserDto.builder().email(req.getParameter("emailUpdate").trim()).login(req.getParameter("loginUpdate").trim()).infoAboutUser(req.getParameter("infoUpdate").trim()).country(req.getParameter("countryUpdate")).build();
        boolean checkEmail = authCheck.checkEmail(User.builder().email(userDto.getEmail()).build());
        if (checkEmail && !userDto.getEmail().equals("") && !userDto.getCountry().equals("") && !userDto.getLogin().equals("")) {
            boolean update = userProfileService.updateUser(userDto, (int) req.getSession().getAttribute("userIdProtected"));
            if (update) {
//                req.getSession().setAttribute("updateSuccess","show");
//                req.getSession().setAttribute("UserNameProfile",userDto.getLogin());
                resp.sendRedirect(req.getContextPath() + "/profile/user");
            } else {
                req.getSession().setAttribute("errOnUpdate", "show");
                req.getRequestDispatcher("/userProfile.jsp").forward(req, resp);
                req.getSession().removeAttribute("errOnUpdate");
            }
        } else {
            req.getSession().setAttribute("errOnUpdate", "show");
            req.getRequestDispatcher("/userProfile.jsp").forward(req, resp);
            req.getSession().removeAttribute("errOnUpdate");
        }
    }
}
