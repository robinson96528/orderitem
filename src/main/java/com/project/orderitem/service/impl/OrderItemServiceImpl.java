package com.project.orderitem.service.impl;

import com.project.orderitem.dao.OrderItemRepository;
import com.project.orderitem.exception.ProductInsertionFailure;
import com.project.orderitem.exception.ProductUnavailable;
import com.project.orderitem.model.Product;
import com.project.orderitem.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> orderedProdList= new LinkedList<>();
        orderItemRepository.findAll().forEach(orderedProdList::add);
        if (orderedProdList.isEmpty()) {
            throw new ProductUnavailable();
        }else
            return new ResponseEntity<>(orderedProdList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        Optional<Product> orderedProduct = orderItemRepository.findById(id);
        return orderedProduct.map(order -> new ResponseEntity<>(order, HttpStatus.OK)).orElseThrow(ProductUnavailable::new);
    }

    @Override
    public ResponseEntity<List<Product>> getProductsById(List<Long> id) {
        Iterable<Product> orderedProduct = orderItemRepository.findAllById(id);
        List<Product> products=new LinkedList<>();
        for (Product product : orderedProduct) {
            products.add(product);
        }
        if(!products.isEmpty()){
            return new ResponseEntity<>(products, HttpStatus.OK);
        }else {
            throw new ProductUnavailable();
        }
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        if(product != null) {
            Product prod =orderItemRepository.save(product);
            return new ResponseEntity<>(prod, HttpStatus.OK);
        }
        else
            throw new ProductInsertionFailure();
    }

    @Override
    public ResponseEntity<Object> updateProduct(Map<Long, Integer> productList) {
        productList.forEach((key, value) -> {
            Optional<Product> productOptional = orderItemRepository.findById(key);
            productOptional.map(elem -> {
                elem.setQuantity(value);
                return orderItemRepository.save(elem);
            }).orElseThrow(ProductUnavailable::new);
        });
        return new ResponseEntity<>("Sucesss", HttpStatus.OK);
    }
}
