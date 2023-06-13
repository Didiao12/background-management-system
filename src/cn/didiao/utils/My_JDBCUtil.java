package cn.didiao.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class My_JDBCUtil {
   static DataSource ds;

   static {
       Properties pro= new Properties();

       try {
           pro.load(My_JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
           System.out.println(pro.getProperty("driverClassName"));
           ds = DruidDataSourceFactory.createDataSource(pro);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

   public static Connection getConnection() throws SQLException {
       return ds.getConnection();
   }

   public static DataSource getDataSource(){
       return ds;
   }
}
