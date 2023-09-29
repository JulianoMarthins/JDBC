package com.julianoMarthins.jdbc;

public class DbException extends RuntimeException {

    public DbException(String mensagem){
        super(mensagem);
    }

}
