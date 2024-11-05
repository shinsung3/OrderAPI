package com.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductDTO {
    private Integer id; // null 가능
    private String name;
    private Integer price; // null 가능
    private Integer stock; // null 가능

    public ProductDTO() {}
}
