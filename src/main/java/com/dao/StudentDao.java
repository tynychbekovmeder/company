package com.dao;

import com.model.Group;
import com.model.Student;

import java.util.List;

public interface StudentDao {

    void save(Student student, Long id);

    List<Student> getAllStudent();

    Student getStudentById(Long id);

    void updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
