package com.hibernate.service.persistence;

import com.hibernate.model.Group;
import com.hibernate.model.dto.GroupDto;
import com.hibernate.repository.GroupRepository;
import com.hibernate.service.GroupService;
import com.hibernate.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepo;
    private final Mapper<Group, GroupDto> mapper;

    @Override
    public GroupDto save(GroupDto groupDto) {
        Group group = mapper.toEntity(groupDto);
        groupRepo.save(group);
        return mapper.toDto(group);
    }

    @Override
    public GroupDto findById(Long id) {
        Group group = groupRepo.findById(id);
        return mapper.toDto(group);
    }

    @Override
    public List<GroupDto> findAll() {
        return groupRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        groupRepo.deleteById(id);
    }

}
