package com.hibernate.service;

import com.hibernate.model.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto save(StudentDto studentDto);

    StudentDto findById(Long id);

    List<StudentDto> findAll();

    void deleteById(Long id);
}
