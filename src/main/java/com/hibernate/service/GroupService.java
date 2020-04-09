package com.hibernate.service;

import com.hibernate.model.dto.GroupDto;

import java.util.List;

public interface GroupService {
    GroupDto save(GroupDto groupDto);

    GroupDto findById(Long id);

    List<GroupDto> findAll();

    void deleteById(Long id);
}
