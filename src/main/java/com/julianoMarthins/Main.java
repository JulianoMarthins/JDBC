package com.julianoMarthins;

import java.util.Date;

import com.julianoMarthins.model.dao.DaoFactory;
import com.julianoMarthins.model.dao.SellerDao;
import com.julianoMarthins.model.entities.Department;
import com.julianoMarthins.model.entities.Seller;

public class Main {
    public static void main(String[] args) {

        Department department = new Department(5, "Books");

        Seller seller = new Seller(5, "Juliano", "Juliano@Gmail", new Date(), 3000.0,
        department);

        SellerDao sellerDao = DaoFactory.createSellerDao();



        System.out.println(seller);

    }
}
