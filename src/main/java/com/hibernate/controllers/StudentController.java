package com.hibernate.controllers;

import com.hibernate.model.dto.StudentDto;
import com.hibernate.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> save(@Valid @RequestBody StudentDto studentDto) {
        StudentDto dto = studentService.save(studentDto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        StudentDto studentDto = studentService.findById(id);
        return ResponseEntity.ok().body(studentDto);
    }

    @GetMapping(value = "/show", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> studentDtoList = studentService.findAll();
        return ResponseEntity.ok().body(studentDtoList);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.ok().body("Student with id = " + id + " successfully deleted");
    }

}
