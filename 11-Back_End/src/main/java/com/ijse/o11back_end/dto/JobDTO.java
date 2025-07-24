package com.ijse.o11back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private Integer id;
    @NotBlank(message = "Job Title is Mandatory")
    private String jobTitle;
    @NotBlank(message = "Company is Mandatory")
    @Pattern(regexp = "^[A-Za-z]+$\",message = \"company Name Should Contain Alphabe Lettres")
    private String company;
    private String location;
    private String type;
    private String jobDescription;
    private String status;
}
