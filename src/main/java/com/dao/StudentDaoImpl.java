package com.dao;

import com.model.Group;
import com.model.Student;
import com.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao{

    GroupService groupService;

    @Autowired
    public StudentDaoImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Student student, Long id) {
        student.setGroup(groupService.getGroupById(id));
        entityManager.persist(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return entityManager.createQuery("SELECT student FROM Student student",Student.class).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = getStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        entityManager.remove(getStudentById(id));
    }
}
