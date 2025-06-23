package com.servermgmt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecificationDTO {
    private Long id;

    @NotBlank(message = "Specification name is required")
    private String specName;

    @NotBlank(message = "Specification value is required")
    private String specValue;

    private Long equipmentId;
}