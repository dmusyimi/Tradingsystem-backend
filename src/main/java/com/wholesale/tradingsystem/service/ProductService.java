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

import java.math.BigDecimal;
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

    public ProductDTO getProductById(String id) {
        return productRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Log the incoming DTO for debugging
        System.out.println("Creating product with DTO: " + productDTO);

        // Validate required fields
        if (productDTO.getProductName() == null || productDTO.getProductName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (productDTO.getQuantityInStock() == null || productDTO.getQuantityInStock() < 0) {
            throw new IllegalArgumentException("Quantity in stock must be a non-negative number");
        }
        if (productDTO.getBuyPrice() == null || productDTO.getBuyPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Buy price must be a non-negative number");
        }
        if (productDTO.getMsrp() == null || productDTO.getMsrp().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("MSRP must be a non-negative number");
        }

        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Transactional
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        if (!productRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Product not found with id: " + id, 1);
        }

        Product product = convertToEntity(productDTO);
        product.setId(id); // ensure we update the existing entity
        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    @Transactional
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Product not found with id: " + id, 1);
        }
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getProductsByProductLine(String productLine) {
        return productRepository.findByProductLine_ProductLine(productLine)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setProductLineId(product.getProductLine().getId());
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
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setProductName(dto.getProductName());

        // Find and set the product line by ID
        if (dto.getProductLineId() == null || dto.getProductLineId().trim().isEmpty()) {
            throw new IllegalArgumentException("ProductLine ID cannot be null or empty");
        }

        ProductLine productLine = productLineRepository.findById(dto.getProductLineId())
                .orElseThrow(() -> new EntityNotFoundException("ProductLine not found with id: " + dto.getProductLineId() + ". Available ProductLines: " + productLineRepository.count()));
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