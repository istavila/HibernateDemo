package com.hibernate.repository.impl;

import com.hibernate.model.Student;
import com.hibernate.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class StudentRepoImpl implements StudentRepository {

    private final SessionFactory sessionFactory;


    @Override
    public void save(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }

    @Override
    public List<Student> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    public Student findById(Long id) {
        return sessionFactory.getCurrentSession().find(Student.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.load(Student.class, id);
        session.delete(student);
    }
}
