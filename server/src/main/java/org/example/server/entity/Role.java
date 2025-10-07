package org.example.server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid default uuid_generate_v4()")
    private UUID id;

    @Column(nullable = false,name = "role_name")
    private String roleName;

    @Column(nullable = false, name = "role_code")
    private String roleCode;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Role role = (Role) object;
        return Objects.equals(id, role.id) && Objects.equals(roleName, role.roleName) && Objects.equals(roleCode, role.roleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, roleCode);
    }

    @Override
    public String getAuthority() {
        return this.roleCode;
    }
}
