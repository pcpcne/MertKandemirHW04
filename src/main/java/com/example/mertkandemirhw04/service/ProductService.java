package com.example.mertkandemirhw04.service;

import com.example.mertkandemirhw04.entity.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    public ResponseEntity<?> addProduct(Product product);

    public ResponseEntity<?> updateProduct(Product product);

    public ResponseEntity<?> findProductById(Long id);

    public ResponseEntity<?> deleteProductById(Long id);
}
