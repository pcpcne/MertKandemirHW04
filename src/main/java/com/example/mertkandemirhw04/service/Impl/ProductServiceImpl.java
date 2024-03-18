package com.example.mertkandemirhw04.service.Impl;

import com.example.mertkandemirhw04.entity.Product;
import com.example.mertkandemirhw04.enums.PEnum;
import com.example.mertkandemirhw04.repository.ProductRepository;
import com.example.mertkandemirhw04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import static com.example.mertkandemirhw04.enums.PEnum.*;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public ResponseEntity<?> addProduct(Product product) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        boolean hasProductName = productRepository.existsByProductName(product.getProductName());
        if(hasProductName){
            hashMap.put(productName,product.getProductName());
            hashMap.put(status,false);
            hashMap.put(message,"Product Already Exists.");
            hashMap.put(result,product);

            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
        productRepository.save(product);
        hashMap.put(productName,product.getProductName());
        hashMap.put(status,true);
        hashMap.put(message,"Product Created.");
        hashMap.put(result,product);

        return new ResponseEntity<>(hashMap,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
        if(optionalProduct.isPresent()){
            productRepository.saveAndFlush(product);
            hashMap.put(productName,product.getProductName());
            hashMap.put(status,true);
            hashMap.put(message,"Product Updated.");
            hashMap.put(result,product);

            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(productName,product.getProductName());
        hashMap.put(status,false);
        hashMap.put(error,"Product Not Found.");

        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findProductById(Long id) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        Product product = productRepository.findById(id).orElse(null);
        if(product!=null){
            hashMap.put(productName,product.getProductName());
            hashMap.put(status,true);
            hashMap.put(message,"Product Found.");
            hashMap.put(result,product);

            return new ResponseEntity<>(hashMap,HttpStatus.FOUND);
        }
        hashMap.put(status,false);
        hashMap.put(error,"Product Not Found.");

        return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> deleteProductById(Long id) {
        HashMap<PEnum,Object> hashMap = new HashMap<>();
        boolean hasProduct = productRepository.existsById(id);
        if(hasProduct){
            productRepository.deleteById(id);
            hashMap.put(status,true);
            hashMap.put(message,"Product Deleted.");

            return new ResponseEntity<>(hashMap,HttpStatus.OK);
        }
        hashMap.put(status,false);
        hashMap.put(error,"Product Not Found.");

        return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
    }
}
