package com.codewithmosh.store.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String description;
    private Byte categoryId;
}
