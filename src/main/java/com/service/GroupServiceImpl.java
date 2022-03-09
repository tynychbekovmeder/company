package com.service;

import com.dao.GroupDao;
import com.model.Course;
import com.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    @Transactional
    @Override
    public void save(Group group, Long id) {
        groupDao.save(group,id);
    }

    @Transactional
    @Override
    public List<Group> getAllGroup() {
        return groupDao.getAllGroup();
    }

    @Transactional
    @Override
    public Group getGroupById(Long id) {
        return groupDao.getGroupById(id);
    }

    @Transactional
    @Override
    public void updateGroup(Long id, Group group) {
        groupDao.updateGroup(id, group);
    }

    @Transactional
    @Override
    public void deleteGroup(Long id) {
        groupDao.deleteGroup(id);
    }
}
