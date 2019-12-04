package com.company.servlets.packedServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@WebServlet("/CurrentTime")
public class ServerTimeOnlineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OutputStream outputStream = resp.getOutputStream();
        Date date = new Date();
        resp.setContentType("text/plain");
        outputStream.write((date.toString()).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
