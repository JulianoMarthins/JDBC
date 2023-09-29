package com.julianoMarthins;

import com.julianoMarthins.jdbc.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InserirDados {
    public static void main(String[] args){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection connect = null;
        PreparedStatement state = null;

        try {
            connect = DB.getConnection();

            state = connect.prepareStatement(
                    "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)" +
                            "values" +
                            "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            /*
            state.setString(1, "Alvaro Duran");
            state.setString(2, "duram@gmail.com");

            //state.setDate(3, Date.valueOf("1945-09-07 00:00:00"));
            state.setDate(3, new Date(sdf.parse("09/12/1987").getTime()));

            state.setDouble(4, 2500.00);
            state.setInt(5, 1);


             */

            state = connect.prepareStatement(
                "insert into department (Name) values  ('Music'),('Games')",
                Statement.RETURN_GENERATED_KEYS
            );




            int rowsAffected = state.executeUpdate();

            if(rowsAffected > 0){
                ResultSet chaveGerada = state.getGeneratedKeys();
                while (chaveGerada.next()){
                    int id = chaveGerada.getInt(1);
                    System.out.println("Done! ID = " + id);
                }
            } else{
                System.out.println("Nenhum linha alterada");
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(state);
            DB.closeConection();
        }

    }

}
