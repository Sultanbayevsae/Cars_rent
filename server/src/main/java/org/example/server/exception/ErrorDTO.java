package org.example.server.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@Schema(
        description = "Server da sodir bo`lgan error lardan qaytadigan object (json)"
)
public class ErrorDTO {
    @Schema(
            description = "error sodir bo`lgan API", example = "/example/example"
    )
    private String errorPath;
    @Schema(
            description = "error code", example = "400"
    )
    private Integer errorCode;

    @Schema(
            description = "error sodir bo`lish sababi", example = "bunday email oldin foydalanilgan."
    )
    private Object errorBody;

    @Builder.Default
    @Schema(
            description = "error sodir bo`lgan vaqt", example = "2025-09-10T17:44:15.101677"
    )
    private LocalDateTime timestamp = LocalDateTime.now();
}
