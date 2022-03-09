package com.dao;

import com.model.Course;
import com.model.Group;

import java.util.List;

public interface GroupDao {

    void save(Group group, Long id);

    List<Group> getAllGroup();

    Group getGroupById(Long id);

    void updateGroup(Long id, Group group);

    void deleteGroup(Long id);
}
