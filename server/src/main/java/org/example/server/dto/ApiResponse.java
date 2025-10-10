package org.example.server.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        description = "API dan qaytadigan JSON"
)
public class ApiResponse {

    @Schema(description = "Agar request dan muvaffaqiyatli response qaytsa. Ya'ni xatolik sodir bo`lmasa true aks holda false qiymat qaytadi", example = "true")
    private Boolean success;

    @Schema(description = "API dan qaytgan xabar", example = "User yaratildi!")
    private String message;

    @Schema(description = "API dan qaytgan data (JSON object)")
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
