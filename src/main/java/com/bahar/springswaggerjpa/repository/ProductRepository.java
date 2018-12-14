package com.bahar.springswaggerjpa.repository;

import com.bahar.springswaggerjpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: B_Adineh
 * Date: 12/14/2018
 * Time: 1:46 PM
 * To change this template use File | Settings | File and Code Templates.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}