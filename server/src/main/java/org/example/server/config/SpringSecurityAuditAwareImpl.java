package org.example.server.config;

import lombok.extern.slf4j.Slf4j;
import org.example.server.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//===FIXED BY CLAUDE
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class SpringSecurityAuditAwareImpl implements AuditorAware<UUID> {

    private static final UUID SYSTEM_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            log.debug("No authenticated user, using SYSTEM_USER_ID");
            return Optional.of(SYSTEM_USER_ID);
        }

        Object principal = auth.getPrincipal();
        if (principal instanceof User user) {
            return Optional.ofNullable(user.getId());
        }

        log.warn("Principal is not a User instance: {}", principal.getClass().getName());
        return Optional.of(SYSTEM_USER_ID);
    }
}