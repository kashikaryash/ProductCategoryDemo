package com.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", length = 15)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}