package com.hibernate.service.persistence;

import com.hibernate.model.Student;
import com.hibernate.model.dto.StudentDto;
import com.hibernate.repository.GroupRepository;
import com.hibernate.repository.StudentRepository;
import com.hibernate.service.StudentService;
import com.hibernate.service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Setter
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final GroupRepository groupRepository;
    private final Mapper<Student, StudentDto> mapper;

    @Override
    public StudentDto save(StudentDto studentDto) {
        Student student = mapper.toEntity(studentDto);
        student.setGroup(groupRepository.findById(studentDto.getGroupId()));
        studentRepo.save(student);
        return mapper.toDto(student);
    }

    @Override
    public StudentDto findById(Long id) {
        Student student = studentRepo.findById(id);
        return mapper.toDto(student);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepo.findAll()
                 .stream()
                 .map(mapper::toDto)
                 .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        studentRepo.deleteById(id);
    }

}
