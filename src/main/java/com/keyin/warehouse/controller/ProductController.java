package com.keyin.warehouse.controller;

import com.keyin.warehouse.model.Product;
import com.keyin.warehouse.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/sorted")
    public List<Product> getSorted(@RequestParam String by) {

        List<Product> products = productService.getAllProducts();

        if (by.equalsIgnoreCase("price")) {
            return productService.sortByPrice(products);
        }

        if (by.equalsIgnoreCase("stock")) {
            return productService.sortByStock(products);
        }

        throw new IllegalArgumentException("Invalid sort type. Use price or stock.");
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}