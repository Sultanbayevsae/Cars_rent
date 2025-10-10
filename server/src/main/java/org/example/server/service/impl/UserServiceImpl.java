package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.dto.ApiResponse;
import org.example.server.entity.User;
import org.example.server.repository.UserRepository;
import org.example.server.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse activateUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse(false, "❌ User topilmadi!");
        }

        User user = optionalUser.get();
        if (user.isEnabled()) {
            return new ApiResponse(true, "✅ Siz allaqachon aktivlashtirilgansiz!");
        }

        user.setEnabled(true);
        userRepository.save(user);

        return new ApiResponse(true, "🎉 Profilingiz muvaffaqiyatli aktivlashtirildi!");
    }

}