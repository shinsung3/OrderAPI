package com.order.controller;

import com.order.dto.ProductDTO;
import com.order.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
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
    public ResponseEntity<String> reduceStock(@PathVariable Integer id, @RequestParam int quantity) {
        productService.deductStock(id, quantity);
        return ResponseEntity.ok("재고가 차감되었습니다.");
    }
}
