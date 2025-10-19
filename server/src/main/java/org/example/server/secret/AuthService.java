package org.example.server.secret;

import lombok.RequiredArgsConstructor;
import org.example.server.repository.UserRepository;
import org.mapstruct.ap.spi.BuilderInfo;
import org.mapstruct.ap.spi.NoOpBuilderProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.lang.model.type.TypeMirror;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
   private final UserRepository userRepository;

   @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByEmail(username).get();
    }
}
