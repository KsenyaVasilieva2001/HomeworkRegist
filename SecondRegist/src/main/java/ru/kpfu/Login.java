package ru.kpfu;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class Login extends HttpServlet {

    static final String url = "jdbc:postgresql://rc1b-kzatpd47g08c03ae.mdb.yandexcloud.net:6432/db_users";
    static final String username = "ksenya0v4silieva";
    static final String password = "felix1234";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/log.jsp").forward(req, resp);
        PrintWriter out = resp.getWriter();
        getServletContext().getRequestDispatcher("/log.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        try {
            if(Validate.checkUser(email, pass)) {
                out.println("Welcome to our site!");
            }
            else
            {
                request.setAttribute("status", "Username or Password incorrect");
                doGet(request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
