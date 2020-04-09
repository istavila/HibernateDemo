package com.hibernate.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {
    private Long groupId;
    private String groupName;
}
