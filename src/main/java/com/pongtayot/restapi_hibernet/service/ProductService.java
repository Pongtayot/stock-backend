package com.pongtayot.restapi_hibernet.service;

import com.pongtayot.restapi_hibernet.controllers.request.ProductRequest;
import com.pongtayot.restapi_hibernet.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(ProductRequest productRequest);

    Product updateProduct(ProductRequest productRequest, Long id);

    void deleteProduct(Long id);

    Product getProductByName(String name);

    List<Product> getProductByNameAndStock(String name, int stock);

    List<Product> checkOutOfStock();

    List<Product> getProductByNameAndPrice(String name, int price);
}
