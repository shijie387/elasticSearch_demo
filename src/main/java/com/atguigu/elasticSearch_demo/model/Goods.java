package com.atguigu.elasticSearch_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: atguigu
 * @create: 2024-02-23 14:33
 */
@Data
@AllArgsConstructor
public class Goods {
    private String id;
    private String title;
    private String images;
    private String brand;
    private Integer price;
}
