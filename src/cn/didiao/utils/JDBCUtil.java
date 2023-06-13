package cn.didiao.utils;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

public class JDBCUtil {

    private static String url;
    private static String uses;
    private static String password;
    private static String Driver;
    private static Integer min;
    private static Integer max;
    private static LinkedList<Connection> list= new LinkedList<>();

    static {
        init();
        for (int i = 0; i <min ; i++) {
            try {
                list .addLast(DriverManager.getConnection(url, uses, password));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void init(){
        Properties p = new Properties();
        try {
            p.load(JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            url = p.getProperty("url");
            uses = p.getProperty("uses");
            password = p.getProperty("password");
            Driver = p.getProperty("Driver");
            Class.forName(Driver);
            min = Integer.parseInt(p.getProperty("min"));
            max = Integer.parseInt(p.getProperty("max"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){

        if (list.size()>0){
            return list.removeFirst();
        }
        try {
            return DriverManager.getConnection(url,uses,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection con){
        if (list.size()<max){
            list.addLast(con);
        }else {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }



    public static void closeAll(Connection con, Statement stm){
        closeAll(con,stm,null);
    }
    public static void closeAll(Connection con, Statement stm, ResultSet rs){

       if (rs!=null){
           try {
               rs.close();
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
       }
        if (stm!=null){
            try {
                stm.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        close(con);
    }
}
