package com.service;

import com.model.Course;
import com.model.Group;

import java.util.List;

public interface GroupService {

    void save(Group group, Long id);

    List<Group> getAllGroup();

    Group getGroupById(Long id);

    void updateGroup(Long id, Group group);

    void deleteGroup(Long id);
}
