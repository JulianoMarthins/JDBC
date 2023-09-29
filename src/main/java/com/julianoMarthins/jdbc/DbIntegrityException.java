package com.julianoMarthins.jdbc;

public class DbIntegrityException extends RuntimeException{

    public DbIntegrityException(String mensagem){
        super(mensagem);
    }

}

