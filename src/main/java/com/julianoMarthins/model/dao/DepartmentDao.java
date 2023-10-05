package com.julianoMarthins.model.dao;

import com.julianoMarthins.model.entities.Department;

import java.util.List;

public interface DepartmentDao {

    void insert(Department obj); // Inserir dados
    void update(Department obj); // Atualizar dados
    void deleteById(Integer id); // Deletar dados usando id como filtro
    Department findById(Integer id); // Encontrar dados usando id como filtro
    List<Department> findAll(); // Retornar toda a lista department

}
