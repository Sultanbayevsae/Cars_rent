package org.example.server.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
