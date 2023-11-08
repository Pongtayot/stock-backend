package com.pongtayot.restapi_hibernet.controllers.api;

import com.pongtayot.restapi_hibernet.controllers.request.ProductRequest;
import com.pongtayot.restapi_hibernet.exception.ValidationException;
import com.pongtayot.restapi_hibernet.model.Product;
import com.pongtayot.restapi_hibernet.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid ProductRequest productRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                throw new ValidationException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            });
        }
        return productService.createProduct(productRequest);
    }

    @PutMapping("/{id}")
    public Product editProduct(@Valid ProductRequest productRequest, BindingResult bindingResult, @PathVariable long id) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                throw new ValidationException(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            });
        }
        return productService.updateProduct(productRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping(path = "/search", params = "name")
    public Product searchProductByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    @GetMapping(path = "/search", params = {"name", "stock"})
    public List<Product> searchProductByNameAndStock(@RequestParam String name, @RequestParam int stock) {
        return productService.getProductByNameAndStock(name, stock);
    }

    @GetMapping("/outOfStock")
    public List<Product> checkOfStock() {
        return productService.checkOutOfStock();
    }

    @GetMapping(path = "/search", params = {"name", "price"})
    public List<Product> searchProductByNameAndPrice(@RequestParam String name, @RequestParam int price) {
        return productService.getProductByNameAndPrice(name, price);
    }
}

