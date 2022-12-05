package com.dao;

import com.model.Course;
import com.model.Group;
import com.service.CompanyService;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao{

    private final CompanyService companyService;
    private final CourseService courseService;

    @Autowired
    public GroupDaoImpl(CompanyService companyService, CourseService courseService) {
        this.companyService = companyService;
        this.courseService = courseService;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Group group, Long id) {
        Course course = courseService.getCourseById(id);
        course.setCourse(group);
        group.setCourse(course);
        entityManager.persist(group);
    }

    @Override
    public List<Group> getAllGroup() {
        return entityManager.createQuery("SELECT g FROM Group g ",Group.class).getResultList();
    }

    @Override
    public Group getGroupById(Long id) {
        Group group = entityManager.find(Group.class,id);
        if (group == null) {
            throw new EntityNotFoundException("Can't find Group for ID " + id);
        }
        return group;
    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = getGroupById(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        group1.setCourseList(group.getCourseList());
        group1.setStudentList(group.getStudentList());
        entityManager.merge(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = getGroupById(id);

        for (Course course: group.getCourseList()){
            group.getCourseList().clear();
        }
        entityManager.remove(group);
    }
}
