package com.pongtayot.restapi_hibernet.service;

import com.pongtayot.restapi_hibernet.controllers.request.ProductRequest;
import com.pongtayot.restapi_hibernet.exception.ProductNotFoundException;
import com.pongtayot.restapi_hibernet.model.Product;
import com.pongtayot.restapi_hibernet.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final StorageServices storageServices;
    private final ProductRepository productRepository;


    ProductServiceImpl(StorageServices storageServices, ProductRepository productRepository) {
        this.storageServices = storageServices;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException(id);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        String fileName = storageServices.store(productRequest.getImage());

        Product data = new Product();
        data.setName(productRequest.getName()).setImage(fileName).setPrice(productRequest.getPrice()).setStock(productRequest.getStock());
        return productRepository.save(data);
    }

    @Override
    public Product updateProduct(ProductRequest productRequest, Long id) {
        String fileName = storageServices.store(productRequest.getImage());
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            if(fileName != null){
                existingProduct.setImage(fileName);
            }
            existingProduct.setName(productRequest.getName()).setPrice(productRequest.getPrice()).setStock(productRequest.getStock());
            return productRepository.save(existingProduct);
        }
        throw new ProductNotFoundException(id);
    }

    @Override
    public void deleteProduct(Long id) {
        try{
            productRepository.deleteById(id);
        }catch (Exception e){
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public Product getProductByName(String name) {
        Optional<Product> product = productRepository.findTopByName(name);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException(name);
    }

    @Override
    public List<Product> getProductByNameAndStock(String name, int stock) {
        return productRepository.findByNameContainingAndStockGreaterThanOrderByStockDesc(name, stock);
    }

    @Override
    public List<Product> checkOutOfStock() {
        return productRepository.checkOutOfStock();
    }

    @Override
    public List<Product> getProductByNameAndPrice(String name, int price) {
        return productRepository.searchNameAndPrice(name, price);
    }
}
