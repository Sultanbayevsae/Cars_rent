package org.example.server.secret;

import lombok.RequiredArgsConstructor;
import org.example.server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
   private final UserRepository userRepository;

   @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByEmail(username).get();
    }
}
