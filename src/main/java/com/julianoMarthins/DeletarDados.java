package com.julianoMarthins;

import com.julianoMarthins.jdbc.DB;
import com.julianoMarthins.jdbc.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarDados {
    public static void main(String[] args) {

        Connection connect = null;
        PreparedStatement state = null;

        try{
            connect = DB.getConnection();

            state = connect.prepareStatement(
                    "DELETE FROM seller " +
                            "WHERE Id = ?");

            state.setInt(1, 14);

            int rowsAffected = state.executeUpdate();

            System.out.println("Banco de dados atualizado\nLinhas afeitadas: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(state);
            DB.closeConection();
        }
    }
}
