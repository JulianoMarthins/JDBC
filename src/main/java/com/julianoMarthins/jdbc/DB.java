package com.julianoMarthins.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conect = null;


    public static Connection getConnection() {
        if (conect == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conect = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conect;
    }

    public static void closeConection() {
        try {
            if ((conect != null)) {
                conect.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {

            Properties props = new Properties();
            props.load(fis);
            return props;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement state){
        if(state != null){
            try {
                state.close();
            } catch (SQLException e){
                throw new DbException(e.getMessage());
            }

        }

    }

    public static void closeResultSet(ResultSet result){
        if(result != null){
            try{
                result.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
