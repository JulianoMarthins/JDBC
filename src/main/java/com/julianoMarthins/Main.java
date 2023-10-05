package com.julianoMarthins;

import java.util.Date;

import com.julianoMarthins.model.dao.DaoFactory;
import com.julianoMarthins.model.dao.SellerDao;
import com.julianoMarthins.model.entities.Department;
import com.julianoMarthins.model.entities.Seller;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("\n\n=== Test 1: Seller findByID ===" );
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);



    }
}
