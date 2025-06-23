package com.servermgmt.mapper;

import com.servermgmt.dto.EquipmentDTO;
import com.servermgmt.model.Equipment;
import com.servermgmt.model.Server;
import com.servermgmt.repository.ServerRepository;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    private final ServerRepository serverRepository;

    public EquipmentMapper(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public EquipmentDTO toDto(Equipment equipment) {
        EquipmentDTO dto = new EquipmentDTO();
        dto.setId(equipment.getId());
        dto.setType(equipment.getType());
        dto.setModel(equipment.getModel());
        dto.setSerialNumber(equipment.getSerialNumber());
        dto.setServerId(equipment.getServer().getId());
        return dto;
    }

    public Equipment toEntity(EquipmentDTO dto) {
        Equipment equipment = new Equipment();
        equipment.setId(dto.getId());
        equipment.setType(dto.getType());
        equipment.setModel(dto.getModel());
        equipment.setSerialNumber(dto.getSerialNumber());

        Server server = serverRepository.findById(dto.getServerId())
                .orElseThrow(() -> new RuntimeException("Server not found"));
        equipment.setServer(server);

        return equipment;
    }
}