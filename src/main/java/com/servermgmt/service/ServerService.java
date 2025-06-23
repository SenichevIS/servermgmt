package com.servermgmt.service;

import com.servermgmt.dto.ServerDTO;
import com.servermgmt.mapper.ServerMapper;
import com.servermgmt.model.Server;
import com.servermgmt.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.servermgmt.exception.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServerService {
    private final ServerRepository serverRepository;
    private final ServerMapper serverMapper;

    @Transactional
    public ServerDTO createServer(ServerDTO serverDTO) {
        Server server = serverMapper.toEntity(serverDTO);
        Server savedServer = serverRepository.save(server);
        return serverMapper.toDto(savedServer);
    }

    @Transactional
    public ServerDTO updateServer(Long id, ServerDTO serverDTO) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Server", id));

        server.setName(serverDTO.getName());
        server.setLocation(serverDTO.getLocation());

        Server updatedServer = serverRepository.save(server);
        return serverMapper.toDto(updatedServer);
    }

    @Transactional
    public void deleteServer(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Server", id));
        serverRepository.delete(server);
    }

    public ServerDTO getServerById(Long id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Server", id));
        return serverMapper.toDto(server);
    }

    public List<ServerDTO> getAllServers() {
        return serverRepository.findAll().stream()
                .map(serverMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ServerDTO> searchServers(String query) {
        return serverRepository.searchAll(query).stream()
                .map(serverMapper::toDto)
                .collect(Collectors.toList());
    }
}