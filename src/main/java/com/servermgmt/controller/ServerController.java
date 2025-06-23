package com.servermgmt.controller;

import com.servermgmt.dto.ApiResponse;
import com.servermgmt.dto.ServerDTO;
import com.servermgmt.service.ServerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
@RequiredArgsConstructor
public class ServerController {
    private final ServerService serverService;

    @PostMapping
    public ResponseEntity<ApiResponse> createServer(@Valid @RequestBody ServerDTO serverDTO) {
        ServerDTO createdServer = serverService.createServer(serverDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Server created", createdServer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateServer(
            @PathVariable Long id,
            @Valid @RequestBody ServerDTO serverDTO) {
        ServerDTO updatedServer = serverService.updateServer(id, serverDTO);
        return ResponseEntity.ok(new ApiResponse(true, "Server updated", updatedServer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteServer(@PathVariable Long id) {
        serverService.deleteServer(id);
        return ResponseEntity.ok(new ApiResponse(true, "Server deleted", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getServerById(@PathVariable Long id) {
        ServerDTO server = serverService.getServerById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Server retrieved", server));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllServers() {
        List<ServerDTO> servers = serverService.getAllServers();
        return ResponseEntity.ok(new ApiResponse(true, "Servers retrieved", servers));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchServers(@RequestParam String query) {
        List<ServerDTO> servers = serverService.searchServers(query);
        return ResponseEntity.ok(new ApiResponse(true, "Search results", servers));
    }
}