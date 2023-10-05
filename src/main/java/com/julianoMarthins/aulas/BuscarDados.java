package com.julianoMarthins.aulas;

import com.julianoMarthins.jdbc.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarDados {
    public static void main(String[] args) {

        Connection conect = null; // Conectar com banco de dados
        Statement state = null; // Realizar uma consulta no banco de dados
        ResultSet result = null; // Resultado que armazenará a consulta

        try {
            conect = DB.getConnection(); // Realizando conexão com banco de dados
            state = conect.createStatement(); //
            result = state.executeQuery("select * from department");

            System.out.print("ID  Name\n");
            while (result.next()) {
                System.out.println(result.getInt("Id") + " - " + result.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(result);
            DB.closeStatement(state);
            DB.closeConection();
        }


    }
}