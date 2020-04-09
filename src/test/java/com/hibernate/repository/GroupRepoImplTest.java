package com.hibernate.repository;

import com.hibernate.config.AppConfig;
import com.hibernate.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
public class GroupRepoImplTest {

    public final String GROUP_NAME = "Endava";

    @Autowired
    private GroupRepository groupRepository;

    private Group group;

    @BeforeEach()
    public void setUp() {
        group = new Group(null, GROUP_NAME, null);
    }

    @Test
    public void shouldSaveGroupInTheDatabase() {
        groupRepository.save(group);
        assertThat(groupRepository.findAll()).contains(group);
    }

    @Test
    public void shouldFindGroupById() {
        groupRepository.save(group);
        Group group1 = groupRepository.findById(group.getGroupId());
        assertThat(group1).isEqualTo(group);
    }

    @Test
    public void shouldReturnListOfAllGroups() {
        Group group1 = new Group(null, "Moldova", null);
        groupRepository.save(group);
        groupRepository.save(group1);
        List<Group> groups = groupRepository.findAll();
        assertThat(groups).contains(group, group1);
    }

    @Test
    public void shouldDeleteGroupById() {
        groupRepository.save(group);
        groupRepository.deleteById(group.getGroupId());
        assertThat(groupRepository.findAll()).doesNotContain(group);
    }


}
