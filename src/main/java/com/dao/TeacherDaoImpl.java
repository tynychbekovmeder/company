package com.dao;

import com.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDao {

    private final CourseDao courseService;

    @Autowired
    public TeacherDaoImpl(CourseDao courseService) {
        this.courseService = courseService;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Teacher teacher, Long id){
        teacher.setCourse(courseService.getCourseById(id));
        entityManager.persist(teacher);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public void updateTeacher(Long id, Teacher teacher) {
        Teacher teacher1 = getTeacherById(id);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setEmail(teacher.getEmail());
        entityManager.merge(teacher1);
    }

    @Override
    public void deleteTeacher(Long id) {
        entityManager.remove(getTeacherById(id));
    }
}
