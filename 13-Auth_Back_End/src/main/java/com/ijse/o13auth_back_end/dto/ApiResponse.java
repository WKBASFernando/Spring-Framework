package com.ijse.o13auth_back_end.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private Long id;
    private String username;
    private String password;
}
