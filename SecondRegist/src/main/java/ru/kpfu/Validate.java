package ru.kpfu;

import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.sql.*;

public class Validate {
    static final String url = "jdbc:postgresql://rc1b-kzatpd47g08c03ae.mdb.yandexcloud.net:6432/db_users";
    static final String username = "ksenya0v4silieva";
    static final String password = "felix1234";
    public static boolean checkUser(String email,String pass) throws ClassNotFoundException, SQLException {
        boolean st =false;
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("select * from users where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs =ps.executeQuery();
            st = rs.next();
            return st;
    }


}