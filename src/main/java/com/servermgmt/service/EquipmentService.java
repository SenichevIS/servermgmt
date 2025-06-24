package com.servermgmt.service;

import com.servermgmt.dto.EquipmentDTO;
import com.servermgmt.mapper.EquipmentMapper;
import com.servermgmt.model.Equipment;
import com.servermgmt.repository.EquipmentRepository;
import com.servermgmt.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.servermgmt.exception.EntityNotFoundException;
import com.servermgmt.exception.DuplicateSerialNumberException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final ServerRepository serverRepository;
    private final EquipmentMapper equipmentMapper;

    @Transactional
    public EquipmentDTO createEquipment(EquipmentDTO dto) {
        if (equipmentRepository.findBySerialNumber(dto.getSerialNumber()).isPresent()) {
            throw new DuplicateSerialNumberException(dto.getSerialNumber());
        }
        Equipment equipment = equipmentMapper.toEntity(dto);
        Equipment savedEquipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(savedEquipment);
    }

    @Transactional
    public EquipmentDTO updateEquipment(Long id, EquipmentDTO dto) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment", id));

        equipment.setType(dto.getType());
        equipment.setModel(dto.getModel());
        equipment.setSerialNumber(dto.getSerialNumber());

        Equipment updatedEquipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(updatedEquipment);
    }

    @Transactional
    public void deleteEquipment(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment", id));
        equipmentRepository.delete(equipment);
    }

    public EquipmentDTO getEquipmentById(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment", id));
        return equipmentMapper.toDto(equipment);
    }

    public List<EquipmentDTO> getEquipmentByServer(Long serverId) {
        return equipmentRepository.findByServerId(serverId).stream()
                .map(equipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EquipmentDTO> searchEquipment(String query) {
        return equipmentRepository.searchAll(query).stream()
                .map(equipmentMapper::toDto)
                .collect(Collectors.toList());
    }
}