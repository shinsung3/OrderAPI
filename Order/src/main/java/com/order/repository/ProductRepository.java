package com.order.repository;

import com.order.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 상품 재고 차감 메소드
    public ProductDTO reduceStock(int productId, int quantity) {
        String sql = "UPDATE PRODUCT SET stock = stock - ? WHERE id = ?";
        jdbcTemplate.update(sql, quantity, productId);
        return findById(productId);
    }

    // 상품 정보 조회 메소드
    public ProductDTO findById(int productId) {
        String sql = "SELECT * FROM PRODUCT WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{productId}, (rs, rowNum) -> {
            ProductDTO product = new ProductDTO();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setStock(rs.getInt("stock"));
            return product;
        });
    }


//    public void insertProduct(ProductDTO product) throws SQLException {
//        String sql = "INSERT INTO users (name, price, stock) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getStock());
//    }

    // 데이터 조회
    public List<ProductDTO> selectAll() throws SQLException {
        String sql = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(sql, new ProductList());
    }

    private static class ProductList implements RowMapper<ProductDTO> {
        @Override
        public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductDTO product = new ProductDTO();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setStock(rs.getInt("stock"));
            return product;
        }
    }
}
