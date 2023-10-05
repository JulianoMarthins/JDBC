package com.julianoMarthins.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    /*
    A implementação Serializable serve para que os objetos criado possam ser transformados em uma sequência de bites,
    na linguagem Java é obrigatório a implementação do Serializable caso desejarmos que o objeto seja gravado em
    arquivos, trafegado em rede. Será necessário adicionar o código com o número de versão conforme código abaixo;
     */

    private static final long serialVersionUID = 1L;

    private Integer departmentId;
    private String departmentName;

    public Department(int id, String name) {
        this.departmentId = id;
        this.departmentName = name;
    }

    public Department(){

    }

    public void setId(int id) {
        this.departmentId = id;
    }

    public Integer getId() {
        return this.departmentId;
    }

    public void setName(String name) {
        this.departmentName = name;
    }

    public String getName() {
        return this.departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId);
    }

    public String toString() {
        return "----------------------------------------\nDepartment:\nId: " + getId() + "\nName: " + getName() + "\n";
    }
}
