package org.example.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(
        description = "Response after logging in"
)
public class AuthResponse {
    @Schema(
            description = "accessToken is the value of JWT token"
    )
    private String accessToken;

    @Schema(
            description = "tokenType - JWT token type", example = "Bearer"
    )
    private String tokenType = "Bearer";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}