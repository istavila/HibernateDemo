package com.hibernate.repository.impl;

import com.hibernate.model.Group;
import com.hibernate.repository.GroupRepository;
import com.hibernate.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class GroupRepoImpl implements GroupRepository {

    private final SessionFactory sessionFactory;
    private final StudentRepository studentRepository;

    @Override
    public void save(Group group) {
        sessionFactory.getCurrentSession().save(group);
    }

    @Override
    public List<Group> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT g FROM Group g", Group.class)
                .getResultList();
    }

    @Override
    public Group findById(Long id) {
        return sessionFactory.getCurrentSession().find(Group.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Group group = session.load(Group.class, id);
        session.delete(group);
    }
}
