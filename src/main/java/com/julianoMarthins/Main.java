package com.julianoMarthins;


import com.julianoMarthins.model.dao.DaoFactory;
import com.julianoMarthins.model.dao.SellerDao;
import com.julianoMarthins.model.entities.Department;
import com.julianoMarthins.model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("\n===== Test 1: Seller findByID =====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);


        System.out.println("\n\n===== Test 2: Seller findByDepartment =====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller obj : list) {
            System.out.println(obj);
        }


        System.out.println("\n\n===== Test 4: Seller findAll =====");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }


        System.out.println("\n\n===== Test 4: Seller insert =====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0,
                department);
        sellerDao.insert(newSeller);
        System.out.println("Insert! New id = " + newSeller.getId());


        System.out.println("\n\n===== Test 5: Seller update =====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        seller.setEmail("martha@gamil.com");
        sellerDao.update(seller);
        System.out.println("Update completed");


    }
}
