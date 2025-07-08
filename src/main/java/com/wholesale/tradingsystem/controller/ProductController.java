package com.wholesale.tradingsystem.controller;

import com.wholesale.tradingsystem.dto.ProductDTO;
import com.wholesale.tradingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String productCode) {
        return ResponseEntity.ok(productService.getProductById(productCode));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Validated @RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.createProduct(productDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable String productCode,
            @Validated @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(productCode, productDTO));
    }

    @DeleteMapping("/{productCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productCode) {
        productService.deleteProduct(productCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productLine/{productLine}")
    public ResponseEntity<List<ProductDTO>> getProductsByProductLine(@PathVariable String productLine) {
        return ResponseEntity.ok(productService.getProductsByProductLine(productLine));
    }
}
