package com.order.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private int price;
    private int stock; // 재고 수량
}
