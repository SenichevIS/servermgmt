package com.servermgmt.controller;

import com.servermgmt.dto.ApiResponse;
import com.servermgmt.dto.EquipmentDTO;
import com.servermgmt.service.EquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> createEquipment(@Valid @RequestBody EquipmentDTO dto) {
        EquipmentDTO createdEquipment = equipmentService.createEquipment(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Equipment created", createdEquipment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEquipment(
            @PathVariable Long id,
            @Valid @RequestBody EquipmentDTO dto) {
        EquipmentDTO updatedEquipment = equipmentService.updateEquipment(id, dto);
        return ResponseEntity.ok(new ApiResponse(true, "Equipment updated", updatedEquipment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.ok(new ApiResponse(true, "Equipment deleted", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getEquipmentById(@PathVariable Long id) {
        EquipmentDTO equipment = equipmentService.getEquipmentById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Equipment retrieved", equipment));
    }

    @GetMapping("/server/{serverId}")
    public ResponseEntity<ApiResponse> getEquipmentByServer(@PathVariable Long serverId) {
        List<EquipmentDTO> equipmentList = equipmentService.getEquipmentByServer(serverId);
        return ResponseEntity.ok(new ApiResponse(true, "Equipment list retrieved", equipmentList));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchEquipment(@RequestParam String query) {
        List<EquipmentDTO> equipmentList = equipmentService.searchEquipment(query);
        return ResponseEntity.ok(new ApiResponse(true, "Search results", equipmentList));
    }
}