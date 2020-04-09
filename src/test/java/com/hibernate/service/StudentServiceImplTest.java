package com.hibernate.service;

import com.hibernate.model.Group;
import com.hibernate.model.Student;
import com.hibernate.model.dto.StudentDto;
import com.hibernate.repository.GroupRepository;
import com.hibernate.repository.StudentRepository;
import com.hibernate.service.mapper.impl.StudentMapper;
import com.hibernate.service.persistence.StudentServiceImpl;
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
public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper mapper;
    @Mock
    private GroupRepository groupRepository;

    public static final String STUDENT_NAME = "Ion";
    public static final int STUDENT_AGE = 23;
    public static final long ID = 1L;
    public static final String GROUP_NAME = "Team";

    private StudentDto studentDto;
    private Student student;
    private Group group;


    @BeforeEach
    public void setUp() {
        studentService = new StudentServiceImpl(studentRepository, groupRepository, mapper);
        studentDto = new StudentDto(ID, STUDENT_NAME, STUDENT_AGE, ID);
        group = new Group(ID, GROUP_NAME, null);
        student = new Student(ID, STUDENT_NAME, STUDENT_AGE, group);
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(mapper, studentRepository, groupRepository);
    }

    @Test
    public void shouldSaveStudent() {

        when(mapper.toEntity(any(StudentDto.class))).thenReturn(student);
        when(groupRepository.findById(ID)).thenReturn(group);
        doNothing().when(studentRepository).save(student);
        when(mapper.toDto(student)).thenReturn(studentDto);

        studentService.save(studentDto);

        verify(mapper).toEntity(studentDto);
        verify(groupRepository).findById(ID);
        verify(studentRepository).save(student);
        verify(mapper).toDto(student);
    }

    @Test
    public void shouldFindStudentById() {

        when(studentRepository.findById(ID)).thenReturn(student);
        when(mapper.toDto(student)).thenReturn(studentDto);

        studentService.findById(ID);

        verify(studentRepository).findById(ID);
        verify(mapper).toDto(student);
    }

    @Test
    public void shouldReturnListOfAllStudent() {

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));
        when(mapper.toDto(student)).thenReturn(studentDto);

        studentService.findAll();

        verify(studentRepository).findAll();
        verify(mapper).toDto(student);
    }

    @Test
    public void shouldDeleteStudentById() {

        doNothing().when(studentRepository).deleteById(ID);
        studentService.deleteById(ID);

        verify(studentRepository).deleteById(ID);
    }

}
