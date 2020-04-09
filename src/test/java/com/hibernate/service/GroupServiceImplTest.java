package com.hibernate.service;

import com.hibernate.model.Group;
import com.hibernate.model.dto.GroupDto;
import com.hibernate.repository.GroupRepository;
import com.hibernate.service.mapper.Mapper;
import com.hibernate.service.persistence.GroupServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTest {

    @InjectMocks
    private GroupServiceImpl groupService;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private Mapper<Group, GroupDto> mapper;

    public static final long ID = 1L;
    public static final String GROUP_NAME = "Team";

    private Group group;
    private GroupDto groupDto;

    @BeforeEach
    public void setUp() {
        groupService = new GroupServiceImpl(groupRepository, mapper);

        group = new Group(ID, GROUP_NAME, null);
        groupDto = new GroupDto(ID, GROUP_NAME);
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(mapper, groupRepository);
    }

    @Test
    public void shouldSaveGroup() {

        when(mapper.toEntity(groupDto)).thenReturn(group);
        doNothing().when(groupRepository).save(group);
        when(mapper.toDto(group)).thenReturn(groupDto);

        groupService.save(groupDto);

        verify(mapper).toEntity(groupDto);
        verify(groupRepository).save(group);
        verify(mapper).toDto(group);
    }

    @Test
    public void shouldFindGroupById() {

        when(groupRepository.findById(ID)).thenReturn(group);
        when(mapper.toDto(group)).thenReturn(groupDto);

        groupService.findById(ID);

        verify(groupRepository).findById(ID);
        verify(mapper).toDto(group);
    }

    @Test
    public void shouldReturnListOfAllGroups() {

        when(groupRepository.findAll()).thenReturn(Arrays.asList(group));
        when(mapper.toDto(group)).thenReturn(groupDto);

        groupService.findAll();

        verify(groupRepository).findAll();
        verify(mapper).toDto(group);
    }

    @Test
    public void shouldDeleteGroupById() {

        doNothing().when(groupRepository).deleteById(ID);
        groupService.deleteById(ID);

        verify(groupRepository).deleteById(ID);
    }

}
