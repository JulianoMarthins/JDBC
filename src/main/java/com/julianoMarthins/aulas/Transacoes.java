package com.julianoMarthins.aulas;

import com.julianoMarthins.jdbc.DB;
import com.julianoMarthins.jdbc.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transacoes {
    public static void main(String[] args) throws SQLException {

        Connection connect = null;

        Statement state = null;

        try {
            connect = DB.getConnection();
            state = connect.createStatement();

            // Método para controlar erros no banco de dados, serve para o programa execute os dois comandos ao banco de
            // dados, caso haja erro no programa durante esses processos, nenhum será enviado ao banco de dados.

            connect.setAutoCommit(false);

            int rows = state.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

//            int x = 1;
//            if (x < 2) {
//                throw new SQLException("Fake Error");
//            }

            int rows2 = state.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            connect.commit(); // Chamada para finalizar os comando ao banco de dados.

            System.out.println("Linhas alteradas no departamento de informática: " + rows);
            System.out.println("Linhas alteradas no departamento de eletrônicos: " + rows2);

        } catch (SQLException e) {
            try {
                connect.rollback();
                throw new DbException("Transaction rolled back! Caused: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused: " + e1.getMessage());
            }
        } finally {
            DB.closeStatement(state);
            DB.closeConection();
        }

    }


}
