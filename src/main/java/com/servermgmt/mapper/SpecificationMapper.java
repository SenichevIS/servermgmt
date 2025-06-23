package com.servermgmt.mapper;

import com.servermgmt.dto.SpecificationDTO;
import com.servermgmt.model.Specification;
import com.servermgmt.model.Equipment;
import com.servermgmt.repository.EquipmentRepository;
import org.springframework.stereotype.Component;

@Component
public class SpecificationMapper {

    private final EquipmentRepository equipmentRepository;

    public SpecificationMapper(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public SpecificationDTO toDto(Specification spec) {
        SpecificationDTO dto = new SpecificationDTO();
        dto.setId(spec.getId());
        dto.setSpecName(spec.getSpecName());
        dto.setSpecValue(spec.getSpecValue());
        dto.setEquipmentId(spec.getEquipment().getId());
        return dto;
    }

    public Specification toEntity(SpecificationDTO dto) {
        Specification spec = new Specification();
        spec.setId(dto.getId());
        spec.setSpecName(dto.getSpecName());
        spec.setSpecValue(dto.getSpecValue());

        Equipment equipment = equipmentRepository.findById(dto.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        spec.setEquipment(equipment);

        return spec;
    }
}