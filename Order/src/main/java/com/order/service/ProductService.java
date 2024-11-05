package com.order.service;

import com.order.dto.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
//    void inserProduct(ProductDTO product) throws SQLException;
    List<ProductDTO> selectAll() throws SQLException;
    void deductStock(Integer productId, int quantity);
}