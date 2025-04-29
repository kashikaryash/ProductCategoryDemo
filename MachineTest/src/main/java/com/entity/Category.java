package com.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name", length = 15)
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}