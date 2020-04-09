package com.hibernate.service.mapper.impl;

import com.hibernate.model.Student;
import com.hibernate.model.dto.StudentDto;
import com.hibernate.service.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentMapper implements Mapper<Student, StudentDto> {


    @Override
    public Student toEntity(StudentDto dto) {
        return Student.builder()
                .studentId(dto.getId())
                .studentName(dto.getStudentName())
                .studentAge(dto.getStudentAge())
                .build();
    }

    @Override
    public StudentDto toDto(Student entity) {
        return StudentDto.builder()
                .id(entity.getStudentId())
                .studentName(entity.getStudentName())
                .studentAge(entity.getStudentAge())
                .groupId(entity.getGroup().getGroupId())
                .build();
    }

}
