package com.example.mertkandemirhw04.repository;

import com.example.mertkandemirhw04.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String _productName);
}
