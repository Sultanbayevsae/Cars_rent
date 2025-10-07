package org.example.server.config;
import org.example.server.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditAwareImpl implements AuditorAware<UUID> {

    private static final UUID SYSTEM_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals("" +auth.getPrincipal())) {
            Object principal = auth.getPrincipal();
            if(principal instanceof User user) {
                return Optional.ofNullable(user.getId());
            }
        }
        return Optional.of(SYSTEM_USER_ID);

    }
}
