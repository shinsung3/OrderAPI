package com.order.controller;

import com.order.dto.ProductDTO;
import com.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public List<ProductDTO> getProducts() throws SQLException {
        return productService.selectAll();
    }

    // 상품 재고 차감 API
    @PostMapping("/{id}/stock/reduce")
    public ProductDTO reduceStock(@PathVariable int id, @RequestParam int quantity) {
        return productService.deductStock(id, quantity);
//        return ResponseEntity.ok(quantity+" product reduce");
    }
}
