package com.hibernate.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibernate.config.AppConfig;
import com.hibernate.model.dto.StudentDto;
import com.hibernate.service.StudentService;
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
public class StudentControllerTest {

    public static final String STUDENT_NAME = "Ion";
    public static final int STUDENT_AGE = 23;
    public static final long ID = 1L;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private MockMvc mockMvc;

    private StudentDto studentDto;


    @BeforeEach
    public void setUp() {
        studentController = new StudentController(studentService);
        studentDto = new StudentDto(ID, STUDENT_NAME, STUDENT_AGE, ID);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void shouldCallSaveMethodOfServiceAndPerformPostRequest() throws Exception {

        when(studentService.save(studentDto)).thenReturn(studentDto);
        String jsonDto = new ObjectMapper().writeValueAsString(studentDto);

        mockMvc.perform(post("/students/save/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonDto))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonDto));

        verify(studentService).save(studentDto);
    }

    @Test
    public void shouldCallFindByIdMethodAndPerformGetRequest () throws Exception {

        when(studentService.findById(ID)).thenReturn(studentDto);
        String jsonDto = new ObjectMapper().writeValueAsString(studentDto);

        mockMvc.perform(get("/students/find/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonDto));

        verify(studentService).findById(ID);
    }

    @Test
    public void shouldCallFindAllMethodAndPerformGetRequest() throws Exception {

        StudentDto stud = new StudentDto(ID, STUDENT_NAME, STUDENT_AGE, ID);
        List<StudentDto> dtoList = Arrays.asList(studentDto, stud);

        when(studentService.findAll()).thenReturn(dtoList);
        String jsonList = new ObjectMapper().writeValueAsString(dtoList);

        mockMvc.perform(get("/students/show"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(jsonList));

        verify(studentService).findAll();
    }

    @Test
    public void shoudlCallDeleteByIdMethodAndPerformDeleteRequest() throws Exception {

        doNothing().when(studentService).deleteById(ID);
        String expectedString = "Student with id = " + ID + " successfully deleted";

        mockMvc.perform(delete("/students/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedString));

        verify(studentService).deleteById(ID);
    }

}
