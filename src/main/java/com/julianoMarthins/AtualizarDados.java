package com.julianoMarthins;

import com.julianoMarthins.jdbc.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizarDados {
    public static void main(String[] args) {

        Connection connect = null;
        PreparedStatement state = null;

        try {

            connect = DB.getConnection();
            state = connect.prepareStatement(
                    "UPDATE seller " +
                            "SET BaseSalary = BaseSalary + ? " +
                            "WHERE " +
                            "(DepartmentId = ?)");

            state.setDouble(1, 200.00);
            state.setInt(2, 2);

            int rowsAffected  = state.executeUpdate();
            System.out.println("Cadastro atualizado com sucesso: ");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(state);
            DB.closeConection();
        }

    }
}
