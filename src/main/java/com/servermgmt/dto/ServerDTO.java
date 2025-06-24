package com.servermgmt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class ServerDTO {
    private Long id;

    @NotBlank(message = "Server name is required")
    @Size(max = 100, message = "Server name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location cannot exceed 200 characters")
    private String location;

    private Long ownerId;
    private List<EquipmentDTO> equipment;
}