package com.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductDTO {
    private int id; // null 가능
    private String name;
    private int price; // null 가능
    private int stock; // null 가능
}
