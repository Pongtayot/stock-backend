package com.pongtayot.restapi_hibernet.repository;


import com.pongtayot.restapi_hibernet.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// DAO - Data Access Object
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // SELECT * FROM Product WHERE name = "foo" LIMIT 1
    Optional<Product> findTopByName(String name);

    // SELECT * FROM Product WHERE name LIKE '%foo%' AND STOCK > x order by stock desc
    List<Product> findByNameContainingAndStockGreaterThanOrderByStockDesc(String name, int stock);

    //JPQL
    @Query(value = "SELECT * FROM PRODUCT WHERE STOCK = 0", nativeQuery = true)
    List<Product> checkOutOfStock();

    @Query(value = "SELECT * FROM Product WHERE name LIKE %:product_name% AND price > :price", nativeQuery = true)
    List<Product> searchNameAndPrice(@Param("product_name") String name, int price);
}
