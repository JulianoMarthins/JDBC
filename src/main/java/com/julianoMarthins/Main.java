package com.julianoMarthins;



import com.julianoMarthins.model.dao.DaoFactory;
import com.julianoMarthins.model.dao.SellerDao;
import com.julianoMarthins.model.entities.Department;
import com.julianoMarthins.model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("\n===== Test 1: Seller findByID =====" );
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);


        System.out.println("\n\n===== Test 2: Seller findByDepartment =====" );
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for(Seller obj : list){
            System.out.println(obj);
        }


        System.out.println("\n\n===== Test 3: Seller findAll =====" );
        list = sellerDao.findAll();
        for(Seller obj : list){
            System.out.println(obj);
        }


    }
}
