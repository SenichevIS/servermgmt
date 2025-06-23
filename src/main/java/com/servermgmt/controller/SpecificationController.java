package com.servermgmt.controller;

import com.servermgmt.dto.ApiResponse;
import com.servermgmt.dto.SpecificationDTO;
import com.servermgmt.service.SpecificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specifications")
@RequiredArgsConstructor
public class SpecificationController {
    private final SpecificationService specificationService;

    @PostMapping
    public ResponseEntity<ApiResponse> createSpecification(@Valid @RequestBody SpecificationDTO dto) {
        SpecificationDTO createdSpec = specificationService.createSpecification(dto);
        return ResponseEntity.ok(new ApiResponse(true, "Specification created", createdSpec));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSpecification(
            @PathVariable Long id,
            @Valid @RequestBody SpecificationDTO dto) {
        SpecificationDTO updatedSpec = specificationService.updateSpecification(id, dto);
        return ResponseEntity.ok(new ApiResponse(true, "Specification updated", updatedSpec));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSpecification(@PathVariable Long id) {
        specificationService.deleteSpecification(id);
        return ResponseEntity.ok(new ApiResponse(true, "Specification deleted", null));
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<ApiResponse> getSpecificationsByEquipment(@PathVariable Long equipmentId) {
        List<SpecificationDTO> specs = specificationService.getSpecificationsByEquipment(equipmentId);
        return ResponseEntity.ok(new ApiResponse(true, "Specifications retrieved", specs));
    }
}