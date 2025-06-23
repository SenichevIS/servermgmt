package com.servermgmt.mapper;

import com.servermgmt.dto.ServerDTO;
import com.servermgmt.model.Server;
import com.servermgmt.model.User;
import com.servermgmt.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ServerMapper {

    private final UserRepository userRepository;

    public ServerMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ServerDTO toDto(Server server) {
        ServerDTO dto = new ServerDTO();
        dto.setId(server.getId());
        dto.setName(server.getName());
        dto.setLocation(server.getLocation());
        if (server.getOwner() != null) {
            dto.setOwnerId(server.getOwner().getId());
        }
        return dto;
    }

    public Server toEntity(ServerDTO dto) {
        Server server = new Server();
        server.setId(dto.getId());
        server.setName(dto.getName());
        server.setLocation(dto.getLocation());
        if (dto.getOwnerId() != null) {
            User owner = userRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            server.setOwner(owner);
        }
        return server;
    }
}