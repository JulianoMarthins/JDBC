package com.julianoMarthins.model.dao.model.dao.impl;

import com.julianoMarthins.jdbc.DB;
import com.julianoMarthins.jdbc.DbException;
import com.julianoMarthins.model.dao.SellerDao;
import com.julianoMarthins.model.entities.Department;
import com.julianoMarthins.model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connect;

    public SellerDaoJDBC(Connection connect) {
        this.connect = connect;
    }


    @Override
    public void insert(Seller obj) {
        PreparedStatement state = null;

        try {
            state = connect.prepareStatement(
                    "INSERT INTO seller " +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentID " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            state.setString(1, obj.getName());
            state.setString(2, obj.getEmail());
            state.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            state.setDouble(4, obj.getBaseSalary());
            state.setInt(5, obj.getDepartment().getId());

            int rowsAffected = state.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = state.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    obj.setId(id);

                } else {
                    throw new DbException("Unexpected error! No rows affected!");
                }

            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DB.closeStatement(state);
        }
    }


    @Override
    public void update(Seller obj) {
        PreparedStatement state = null;

        try {
            state = connect.prepareStatement(
                    "UPDATE seller " +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                            "WHERE Id = ?");

            state.setString(1, obj.getName());
            state.setString(2, obj.getEmail());
            state.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            state.setDouble(4, obj.getBaseSalary());
            state.setInt(5, obj.getDepartment().getId());
            state.setInt(6, obj.getId());

            state.executeUpdate();

        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DB.closeStatement(state);
        }


    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connect.prepareStatement("SELECT seller.*, department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.departmentId = department.Id " +
                    "WHERE seller.Id = ? ");

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Department department = instantiateDepartment(resultSet);

                Seller seller = instantiateSeller(resultSet, department);

                return seller;

            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }

    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {

        Seller seller = new Seller();

        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setDepartmet(department);

        return seller;
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {

        Department department = new Department();

        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }

    @Override
    public List<Seller> findAll() {

        PreparedStatement state = null;
        ResultSet result = null;

        try {
            state = connect.prepareStatement(
                    "SELECT seller.*, department.Name as DepName " +
                            "From seller INNER JOIN Department " +
                            "ON seller.DepartmentId = department.Id " +
                            "ORDER BY Name");

            result = state.executeQuery();

            List<Seller> lista = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (result.next()) {
                Department dep = map.get(result.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(result);
                    map.put(result.getInt("DepartmentId"), dep);

                }

                Seller obj = instantiateSeller(result, dep);
                lista.add(obj);

            }
            return lista;


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }


    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            state = connect.prepareStatement(
                    "SELECT seller.*, department.Name as DepName " +
                            "From seller INNER JOIN Department " +
                            "ON seller.DepartmentId = department.Id " +
                            "WHERE DepartmentId = ? " +
                            "ORDER BY Name");

            state.setInt(1, department.getId());
            result = state.executeQuery();

            List<Seller> lista = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (result.next()) {
                Department dep = map.get(result.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instantiateDepartment(result);
                    map.put(result.getInt("DepartmentId"), dep);

                }

                Seller obj = instantiateSeller(result, dep);
                lista.add(obj);

            }
            return lista;


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

}
