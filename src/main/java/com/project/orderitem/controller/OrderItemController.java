package com.project.orderitem.controller;

import com.project.orderitem.model.Product;
import com.project.orderitem.model.request.ExternalRequest;
import com.project.orderitem.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("allProducts")
    public ResponseEntity<List<Product>> getAllProduct() {
        return orderItemService.getAllProduct();
    }

    @PostMapping("getProductById")
    public ResponseEntity<Product> getOrderById(@RequestParam("id") Long id) {
        return orderItemService.getProductById(id);
    }

    @RequestMapping(value="getProductsById",method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<List<Product>> getProductsById(@RequestBody ExternalRequest externalRequest) {
        return orderItemService.getProductsById(externalRequest.getProductId());
    }

    @PostMapping("createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return orderItemService.createProduct(product);
    }

    @RequestMapping(value="updateProduct",method =RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Object> updateProduct(@RequestBody ExternalRequest externalRequest){
        return orderItemService.updateProduct(externalRequest.getProductDetails());
    }
}
