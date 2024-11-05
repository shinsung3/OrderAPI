package com.order.service;

import com.order.dto.ProductDTO;
import com.order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Override
//    public void inserProduct(ProductDTO product) throws SQLException {
//        productRepository.insertProduct(product);
//    }

    @Override
    public List<ProductDTO> selectAll() throws SQLException {
        return productRepository.selectAll();
    }

    public ProductDTO deductStock(int productId, int quantity) {
        ProductDTO product = productRepository.findById(productId);
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        productRepository.reduceStock(productId, quantity);
        product = productRepository.findById(productId);
        return product;
    }
}
