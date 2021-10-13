package ru.kpfu;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet(name = "Regist", urlPatterns = {"/regist"})
public class Regist extends HttpServlet {
    static final String url = "jdbc:postgresql://rc1b-kzatpd47g08c03ae.mdb.yandexcloud.net:6432/db_users";
    static final String username = "ksenya0v4silieva";
    static final String password = "felix1234";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        PrintWriter out = resp.getWriter();
        getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String r_pass = request.getParameter("password_repeat");
        try {
            if(pass.equals(r_pass) && pass.length() >= 8 && !Validate.checkUser(email,pass)){
                try {
                    Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
                    try (Connection con = DriverManager.getConnection(url, username, password)) {
                        PreparedStatement ps = con.prepareStatement("insert into users values(?,?,?)");
                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.setString(3, pass);
                        int i = ps.executeUpdate();
                        if(i > 0){
                            out.println("Registration was successfully completed");
                        }
                        else{
                            out.println("Something went wrong");
                        }

                    } catch (Exception se) {
                        se.printStackTrace();
                    }
                }
                catch (Exception e){
                    System.out.println("Connection failed...");
                    System.out.println(e);
                }
                finally {
                    out.close();
                }}
            else {
                if (!pass.equals(r_pass)) { //перенести в валидатор!
                    request.setAttribute("status", "Passwords are not equals!");
                    doGet(request,response);
                }
                if (pass.length() < 8){
                    request.setAttribute("status", "Passwords must be more than 7 symbols");
                    doGet(request,response);
                }
                if (Validate.checkUser(email,pass)){
                    request.setAttribute("status","The user already exists");
                    doGet(request,response);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}