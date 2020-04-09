package com.hibernate.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibernate.config.AppConfig;
import com.hibernate.model.dto.GroupDto;
import com.hibernate.service.GroupService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class GroupControllerTest {

    public static final long ID = 1L;
    public static final String GROUP_NAME = "Team";

    @InjectMocks
    private GroupController groupController;

    @Mock
    private GroupService groupService;

    private MockMvc mockMvc;

    private GroupDto groupDto;

    @BeforeEach
    public void setUp() {
        groupController = new GroupController(groupService);
        groupDto = new GroupDto(ID, GROUP_NAME);
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(groupService);
    }

    @Test
    public void shouldCallSaveMethodAndPerformPostRequest() throws Exception {

        when(groupService.save(groupDto)).thenReturn(groupDto);
        String jsonDto = new ObjectMapper().writeValueAsString(groupDto);

        mockMvc.perform(post("/groups/save/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonDto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonDto));

        verify(groupService).save(groupDto);
    }

    @Test
    public void shouldCallFindByIdMethodAndPerformGetRequest () throws Exception {

        when(groupService.findById(ID)).thenReturn(groupDto);
        String jsonDto = new ObjectMapper().writeValueAsString(groupDto);

        mockMvc.perform(get("/groups/find/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonDto));

        verify(groupService).findById(ID);
    }

    @Test
    public void shouldCallFindAllMethodAndPerformGetRequest() throws Exception {

        GroupDto groupDto1 = new GroupDto(ID, GROUP_NAME);
        List<GroupDto> dtoList = Arrays.asList(groupDto, groupDto1);

        when(groupService.findAll()).thenReturn(dtoList);
        String jsonList = new ObjectMapper().writeValueAsString(dtoList);

        mockMvc.perform(get("/groups/show"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonList));

        verify(groupService).findAll();
    }

    @Test
    public void shoudlCallDeleteByIdMethodAndPerformDeleteRequest() throws Exception {

        doNothing().when(groupService).deleteById(ID);
        String expectedString = "Group with id = " + ID + " successfully deleted";

        mockMvc.perform(delete("/groups/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedString));

        verify(groupService).deleteById(ID);
    }

}
