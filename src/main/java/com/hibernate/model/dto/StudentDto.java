package com.hibernate.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private Long id;
    private String studentName;
    private int studentAge;
    @NotNull
    private Long groupId;
}
