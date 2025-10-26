// ============================================================================
// 6. NEW: RoleRepository.java
// Location: src/main/java/org/example/server/repository/RoleRepository.java
// ============================================================================
package org.example.server.repository;

import org.example.server.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleCode(String roleCode);
    Optional<Role> findByRoleName(String roleName);
}
