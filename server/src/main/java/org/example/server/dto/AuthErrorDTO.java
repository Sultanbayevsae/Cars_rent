package org.example.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthErrorDTO {
    private String message;
    private String errorPath;
    private int status;

    public AuthErrorDTO(String message, String errorPath, int status){
        this.message = message;
        this.errorPath = errorPath;
        this.status = status;
    }
}
