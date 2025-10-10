package org.example.server.service;


import org.example.server.dto.ApiResponse;

import java.util.UUID;

public interface UserService {
    ApiResponse activateUser(UUID id);
}
