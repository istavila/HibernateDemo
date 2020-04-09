package com.hibernate.mapper;

import com.hibernate.model.Group;
import com.hibernate.model.dto.GroupDto;
import com.hibernate.service.mapper.impl.GroupMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupMapperTest {

    private GroupMapper mapper;

    public static final long GROUP_ID = 1l;
    public static final String GROUP_NAME = "Endava";

    @BeforeEach
    public void setUp() {
        mapper = new GroupMapper();
    }

    @Test
    public void shouldReturnGroupEntity() {
        GroupDto dto = GroupDto.builder()
                .groupId(GROUP_ID)
                .groupName(GROUP_NAME)
                .build();

        Group group = mapper.toEntity(dto);

        assertThat(group.getGroupId()).isEqualTo(dto.getGroupId());
        assertThat(group.getGroupName()).isEqualTo(dto.getGroupName());
    }

    @Test
    public void shouldReturnGroupDto() {
        Group group = Group.builder()
                .groupId(GROUP_ID)
                .groupName(GROUP_NAME)
                .build();

        GroupDto dto = mapper.toDto(group);

        assertThat(dto.getGroupId()).isEqualTo(group.getGroupId());
        assertThat(dto.getGroupName()).isEqualTo(group.getGroupName());
    }

}
