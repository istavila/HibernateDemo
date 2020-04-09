package com.hibernate.service.mapper.impl;

import com.hibernate.model.Group;
import com.hibernate.model.dto.GroupDto;
import com.hibernate.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class GroupMapper implements Mapper<Group, GroupDto> {

    @Override
    public Group toEntity(GroupDto dto) {
        return Group.builder()
                .groupId(dto.getGroupId())
                .groupName(dto.getGroupName())
                .build();
    }

    @Override
    public GroupDto toDto(Group entity) {
        return GroupDto.builder()
                .groupId(entity.getGroupId())
                .groupName(entity.getGroupName())
                .build();
    }

}
