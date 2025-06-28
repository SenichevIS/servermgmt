package com.servermgmt.service;

import com.servermgmt.dto.EquipmentDTO;
import com.servermgmt.dto.SpecificationDTO;
import com.servermgmt.mapper.SpecificationMapper;
import com.servermgmt.model.Specification;
import com.servermgmt.repository.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.servermgmt.exception.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecificationService {
    private final SpecificationRepository specificationRepository;
    private final SpecificationMapper specificationMapper;

    @Transactional
    public SpecificationDTO createSpecification(SpecificationDTO dto) {
        Specification spec = specificationMapper.toEntity(dto);
        Specification savedSpec = specificationRepository.save(spec);
        return specificationMapper.toDto(savedSpec);
    }

    @Transactional
    public SpecificationDTO updateSpecification(Long id, SpecificationDTO dto) {
        Specification spec = specificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Specification", id));

        spec.setSpecName(dto.getSpecName());
        spec.setSpecValue(dto.getSpecValue());

        Specification updatedSpec = specificationRepository.save(spec);
        return specificationMapper.toDto(updatedSpec);
    }

    @Transactional
    public void deleteSpecification(Long id) {
        Specification spec = specificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Specification", id));
        specificationRepository.delete(spec);
    }

    public List<SpecificationDTO> getSpecificationsByEquipment(Long equipmentId) {
        return specificationRepository.findByEquipmentId(equipmentId).stream()
                .map(specificationMapper::toDto)
                .collect(Collectors.toList());
    }

    public SpecificationDTO getSpecificationById(Long id) {
        Specification spec = specificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Specification", id));
        return specificationMapper.toDto(spec);
    }

    public List<SpecificationDTO> searchSpecification(String query) {
        return specificationRepository.findBySpecNameContainingIgnoreCase(query).stream()
                .map(specificationMapper::toDto)
                .collect(Collectors.toList());
    }
}