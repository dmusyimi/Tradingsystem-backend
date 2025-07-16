package com.wholesale.tradingsystem.controller;

import com.wholesale.tradingsystem.dto.OfficeDTO;
import com.wholesale.tradingsystem.service.OfficeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping
    public ResponseEntity<List<OfficeDTO>> getAllOffices() {
        return ResponseEntity.ok(officeService.getAllOffices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficeDTO> getOfficeById(@PathVariable String id) {
        return ResponseEntity.ok(officeService.getOfficeById(id));
    }

    @PostMapping
    public ResponseEntity<OfficeDTO> createOffice(@Validated @RequestBody OfficeDTO officeDTO) {
        return new ResponseEntity<>(officeService.createOffice(officeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfficeDTO> updateOffice(
            @PathVariable String id,
            @Validated @RequestBody OfficeDTO officeDTO) {
        return ResponseEntity.ok(officeService.updateOffice(id, officeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffice(@PathVariable String id) {
        officeService.deleteOffice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<OfficeDTO>> getOfficesByCountry(@PathVariable String country) {
        return ResponseEntity.ok(officeService.getOfficesByCountry(country));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<OfficeDTO>> getOfficesByCity(@PathVariable String city) {
        return ResponseEntity.ok(officeService.getOfficesByCity(city));
    }
}