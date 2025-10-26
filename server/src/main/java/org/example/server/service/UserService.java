package org.example.server.service;


import org.example.server.dto.ApiResponse;
import org.example.server.dto.LoginDto;
import org.example.server.dto.RegisterDto;
import org.example.server.entity.User;

import java.util.UUID;

public interface UserService {
    ApiResponse activateUser(UUID id);
    ApiResponse ignoreActivation(UUID id);

}
