package com.ijse.o13auth_back_end.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
}
