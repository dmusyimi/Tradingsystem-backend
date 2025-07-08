package com.wholesale.tradingsystem.service;

import com.wholesale.tradingsystem.dto.OfficeDTO;
import com.wholesale.tradingsystem.model.entity.Office;
import com.wholesale.tradingsystem.repository.OfficeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<OfficeDTO> getAllOffices() {
        return officeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OfficeDTO getOfficeById(String officeCode) {
        return officeRepository.findById(officeCode)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EmptyResultDataAccessException("Office not found with code: " + officeCode, 1));
    }

    @Transactional
    public OfficeDTO createOffice(OfficeDTO officeDTO) {
        Office office = convertToEntity(officeDTO);
        Office savedOffice = officeRepository.save(office);
        return convertToDTO(savedOffice);
    }

    @Transactional
    public OfficeDTO updateOffice(String officeCode, OfficeDTO officeDTO) {
        if (!officeRepository.existsById(officeCode)) {
            throw new EntityNotFoundException("Office not found with code: " + officeCode);
        }

        Office office = convertToEntity(officeDTO);
        office.setOfficeCode(officeCode); // ensure we update the existing entity
        Office updatedOffice = officeRepository.save(office);
        return convertToDTO(updatedOffice);
    }

    @Transactional
    public void deleteOffice(String officeCode) {
        if (!officeRepository.existsById(officeCode)) {
            throw new EmptyResultDataAccessException("Office not found with code: " + officeCode, 1);
        }
        officeRepository.deleteById(officeCode);
    }

    public List<OfficeDTO> getOfficesByCountry(String country) {
        return officeRepository.findByCountry(country)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OfficeDTO> getOfficesByCity(String city) {
        return officeRepository.findByCity(city)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OfficeDTO convertToDTO(Office office) {
        OfficeDTO dto = new OfficeDTO();
        dto.setOfficeCode(office.getOfficeCode());
        dto.setCity(office.getCity());
        dto.setPhone(office.getPhone());
        dto.setAddressLine1(office.getAddressLine1());
        dto.setAddressLine2(office.getAddressLine2());
        dto.setState(office.getState());
        dto.setCountry(office.getCountry());
        dto.setPostalCode(office.getPostalCode());
        dto.setTerritory(office.getTerritory());
        return dto;
    }

    private Office convertToEntity(OfficeDTO dto) {
        Office entity = new Office();
        entity.setOfficeCode(dto.getOfficeCode());
        entity.setCity(dto.getCity());
        entity.setPhone(dto.getPhone());
        entity.setAddressLine1(dto.getAddressLine1());
        entity.setAddressLine2(dto.getAddressLine2());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.setPostalCode(dto.getPostalCode());
        entity.setTerritory(dto.getTerritory());
        return entity;
    }
}
