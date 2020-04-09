package com.hibernate.repository;

import com.hibernate.config.AppConfig;
import com.hibernate.model.Group;
import com.hibernate.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
public class StudentRepoImplTest {

    public final long ID = 1L;
    public final String GROUP_NAME = "Endava";
    public final String STUDENT_NAME = "Ion";
    public final int STUDENT_AGE = 23;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupRepository groupRepository;

    private Group group = Group.builder()
            .groupName(GROUP_NAME)
            .build();

    private Student student;


    @BeforeEach()
    public void setUp() {
        groupRepository.save(group);
        student = new Student(null, STUDENT_NAME, STUDENT_AGE, group);
    }

    @Test
    public void shouldSaveStudentInTheDatabase() {
        studentRepository.save(student);
        assertThat(studentRepository.findAll()).contains(student);
    }

    @Test
    public void shouldFindStudentById() {
        studentRepository.save(student);
        Student stud = studentRepository.findById(student.getStudentId());
        assertThat(stud).isEqualTo(student);
    }

    @Test
    public void shouldReturnListOfAllStudents() {
        Student stud = new Student(null, STUDENT_NAME, STUDENT_AGE, group);
        studentRepository.save(student);
        studentRepository.save(stud);
        assertThat(studentRepository.findAll()).contains(student, stud);
    }

    @Test
    public void shouldDeleteStudentById() {
        studentRepository.save(student);
        studentRepository.deleteById(student.getStudentId());
        assertThat(studentRepository.findAll()).doesNotContain(student);
    }

}
