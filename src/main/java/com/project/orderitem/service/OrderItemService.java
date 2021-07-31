package com.project.orderitem.service;

import com.project.orderitem.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderItemService {
    public ResponseEntity<List<Product>> getAllProduct();

    ResponseEntity<Product> getProductById(Long id);

    ResponseEntity<List<Product>> getProductsById(List<Long> id);

    ResponseEntity<Product> createProduct(Product product);

    ResponseEntity<Object> updateProduct(Map<Long, Integer> productList);
}
