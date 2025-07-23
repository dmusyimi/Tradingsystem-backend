package com.wholesale.tradingsystem.controller;

import com.wholesale.tradingsystem.dto.ProductLineDTO;
import com.wholesale.tradingsystem.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-lines")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductLineController {

    @Autowired
    private ProductLineService productLineService;

    @GetMapping
    public ResponseEntity<List<ProductLineDTO>> getAllProductLines() {
        return ResponseEntity.ok(productLineService.getAllProductLines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductLineDTO> getProductLineById(@PathVariable String id) {
        return ResponseEntity.ok(productLineService.getProductLineById(id));
    }

    @PostMapping
    public ResponseEntity<ProductLineDTO> createProductLine(@Validated @RequestBody ProductLineDTO productLineDTO) {
        return ResponseEntity.ok(productLineService.createProductLine(productLineDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductLineDTO> updateProductLine(
            @PathVariable String id,
            @Validated @RequestBody ProductLineDTO productLineDTO) {
        return ResponseEntity.ok(productLineService.updateProductLine(id, productLineDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductLine(@PathVariable String id) {
        productLineService.deleteProductLine(id);
        return ResponseEntity.noContent().build();
    }
}
