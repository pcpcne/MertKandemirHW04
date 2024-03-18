package com.example.mertkandemirhw04.controller;

import com.example.mertkandemirhw04.entity.Product;
import com.example.mertkandemirhw04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findProductById(@RequestParam Long id){
        return productService.findProductById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductById(@RequestParam Long id){
        return productService.deleteProductById(id);
    }
}
