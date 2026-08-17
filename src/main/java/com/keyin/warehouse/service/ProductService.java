package com.keyin.warehouse.service;

import com.keyin.warehouse.model.Product;
import com.keyin.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> sortByPrice(List<Product> products) {

        for (int i = 1; i < products.size(); i++) {

            Product current = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getPrice() > current.getPrice()) {
                products.set(j + 1, products.get(j));
                j--;
            }

            products.set(j + 1, current);
        }

        return products;
    }

    public List<Product> sortByStock(List<Product> products) {

        for (int i = 1; i < products.size(); i++) {

            Product current = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getStock() > current.getStock()) {
                products.set(j + 1, products.get(j));
                j--;
            }

            products.set(j + 1, current);
        }

        return products;
    }
}