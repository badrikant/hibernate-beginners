package com.learn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author badrikant.soni on Oct,2018
 */
public class TestJdb {

    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-04-one-to-many-uni?useSSL=false";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            System.out.println("Connecting to database:" + jdbcUrl);
            Connection myConnection = DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
