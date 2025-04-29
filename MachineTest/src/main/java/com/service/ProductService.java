package com.service;

import com.entity.Product;
import com.repository.ProductRepository;
import com.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product createProduct(Product product) {
        // Optional validation: check if category exists
        if (product.getCategory() != null) {
            Integer categoryId = product.getCategory().getCategoryId();
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found for ID: " + categoryId));
        }
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setProductName(updatedProduct.getProductName());

        if (updatedProduct.getCategory() != null) {
            Integer newCategoryId = updatedProduct.getCategory().getCategoryId();
            existingProduct.setCategory(categoryRepository.findById(newCategoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found for ID: " + newCategoryId)));
        }

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
