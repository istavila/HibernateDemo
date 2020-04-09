package com.hibernate.mapper;

import com.hibernate.model.Group;
import com.hibernate.model.Student;
import com.hibernate.model.dto.StudentDto;
import com.hibernate.service.mapper.impl.StudentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StudentMapperTest {

    private StudentMapper mapper;

    public static final long STUDENT_ID = 1L;
    public static final String STUDENT_NAME = "Vasea";
    public static final int STUDENT_AGE = 20;
    public static final long GROUP_ID = 1L;
    public static final String GROUP_NAME = "Team";

    @BeforeEach
    public void setUp () {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldReturnStudentEntity() {
        StudentDto dto = StudentDto.builder()
                .id(STUDENT_ID)
                .studentName(STUDENT_NAME)
                .studentAge(STUDENT_AGE)
                .groupId(GROUP_ID)
                .build();

        Student student = mapper.toEntity(dto);

        assertThat(student.getStudentId()).isEqualTo(dto.getId());
        assertThat(student.getStudentName()).isEqualTo(dto.getStudentName());
        assertThat(student.getStudentAge()).isEqualTo(dto.getStudentAge());
    }

    @Test
    public void shouldReturnStudenDto() {
        Group group1 = Group.builder()
                .groupId(GROUP_ID)
                .groupName(GROUP_NAME)
                .build();

        Student student = Student.builder()
                .studentId(STUDENT_ID)
                .studentName(STUDENT_NAME)
                .studentAge(STUDENT_AGE)
                .group(group1)
                .build();

        StudentDto dto = mapper.toDto(student);

        assertThat(dto.getId()).isEqualTo(student.getStudentId());
        assertThat(dto.getStudentName()).isEqualTo(student.getStudentName());
        assertThat(dto.getStudentAge()).isEqualTo(student.getStudentAge());
        assertThat(dto.getGroupId()).isEqualTo(student.getGroup().getGroupId());
    }
}
