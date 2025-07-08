package com.wholesale.tradingsystem.service;

import com.wholesale.tradingsystem.dto.ProductDTO;
import com.wholesale.tradingsystem.model.entity.Product;
import com.wholesale.tradingsystem.model.entity.ProductLine;
import com.wholesale.tradingsystem.repository.ProductRepository;
import com.wholesale.tradingsystem.repository.ProductLineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductLineRepository productLineRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductLineRepository productLineRepository) {
        this.productRepository = productRepository;
        this.productLineRepository = productLineRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(String productCode) {
        return productRepository.findById(productCode)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with code: " + productCode));
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Transactional
    public ProductDTO updateProduct(String productCode, ProductDTO productDTO) {
        if (!productRepository.existsById(productCode)) {
            throw new EmptyResultDataAccessException("Product not found with code: " + productCode, 1);
        }

        Product product = convertToEntity(productDTO);
        product.setProductCode(productCode); // ensure we update the existing entity
        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    @Transactional
    public void deleteProduct(String productCode) {
        if (!productRepository.existsById(productCode)) {
            throw new EmptyResultDataAccessException("Product not found with code: " + productCode, 1);
        }
        productRepository.deleteById(productCode);
    }

    public List<ProductDTO> getProductsByProductLine(String productLine) {
        return productRepository.findByProductLine_ProductLine(productLine)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductCode(product.getProductCode());
        dto.setProductName(product.getProductName());
        dto.setProductLine(product.getProductLine().getProductLine());
        dto.setProductScale(product.getProductScale());
        dto.setProductVendor(product.getProductVendor());
        dto.setProductDescription(product.getProductDescription());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setBuyPrice(product.getBuyPrice());
        dto.setMsrp(product.getMsrp());
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setProductCode(dto.getProductCode());
        entity.setProductName(dto.getProductName());

        // Find and set the product line
        ProductLine productLine = productLineRepository.findById(dto.getProductLine())
                .orElseThrow(() -> new EmptyResultDataAccessException("ProductLine not found: " + dto.getProductLine(), 1));
        entity.setProductLine(productLine);

        entity.setProductScale(dto.getProductScale());
        entity.setProductVendor(dto.getProductVendor());
        entity.setProductDescription(dto.getProductDescription());
        entity.setQuantityInStock(dto.getQuantityInStock());
        entity.setBuyPrice(dto.getBuyPrice());
        entity.setMsrp(dto.getMsrp());
        return entity;
    }
}
