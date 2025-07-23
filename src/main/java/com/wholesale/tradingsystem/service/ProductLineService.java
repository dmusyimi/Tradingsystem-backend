package com.wholesale.tradingsystem.service;

import com.wholesale.tradingsystem.dto.ProductLineDTO;
import com.wholesale.tradingsystem.model.entity.ProductLine;
import com.wholesale.tradingsystem.repository.ProductLineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductLineService {

    @Autowired
    private ProductLineRepository productLineRepository;

    public List<ProductLineDTO> getAllProductLines() {
        return productLineRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductLineDTO getProductLineById(String id) {
        return productLineRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("ProductLine not found with id: " + id));
    }

    @Transactional
    public ProductLineDTO createProductLine(ProductLineDTO productLineDTO) {
        ProductLine productLine = convertToEntity(productLineDTO);
        ProductLine savedProductLine = productLineRepository.save(productLine);
        return convertToDTO(savedProductLine);
    }

    @Transactional
    public ProductLineDTO updateProductLine(String id, ProductLineDTO productLineDTO) {
        if (!productLineRepository.existsById(id)) {
            throw new EntityNotFoundException("ProductLine not found with id: " + id);
        }

        ProductLine productLine = convertToEntity(productLineDTO);
        productLine.setId(id);
        ProductLine updatedProductLine = productLineRepository.save(productLine);
        return convertToDTO(updatedProductLine);
    }

    @Transactional
    public void deleteProductLine(String id) {
        if (!productLineRepository.existsById(id)) {
            throw new EntityNotFoundException("ProductLine not found with id: " + id);
        }
        productLineRepository.deleteById(id);
    }

    private ProductLineDTO convertToDTO(ProductLine productLine) {
        ProductLineDTO dto = new ProductLineDTO();
        dto.setId(productLine.getId());
        dto.setProductLine(productLine.getProductLine());
        dto.setTextDescription(productLine.getTextDescription());
        dto.setHtmlDescription(productLine.getHtmlDescription());
        return dto;
    }

    private ProductLine convertToEntity(ProductLineDTO dto) {
        ProductLine entity = new ProductLine();
        entity.setProductLine(dto.getProductLine());
        entity.setTextDescription(dto.getTextDescription());
        entity.setHtmlDescription(dto.getHtmlDescription());
        return entity;
    }
}
