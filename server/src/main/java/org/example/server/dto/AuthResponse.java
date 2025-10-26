// ============================================================================
// 9. UPDATED: AuthResponse.java (refreshToken field qo'shildi)
// Location: src/main/java/org/example/server/dto/AuthResponse.java
// ============================================================================
package org.example.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Response after logging in")
public class AuthResponse {

    @Schema(description = "Access token (JWT)")
    private String accessToken;

    @Schema(description = "Refresh token (JWT)")
    private String refreshToken;

    @Schema(description = "Token type", example = "Bearer")
    private String tokenType = "Bearer";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
