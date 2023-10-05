package com.julianoMarthins.model.dao;

import com.julianoMarthins.jdbc.DB;
import com.julianoMarthins.model.dao.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

}
