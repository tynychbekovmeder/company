package com.service;

import com.model.Group;
import com.model.Student;

import java.util.List;

public interface StudentService {

    void save(Student student, Long id);

    List<Student> getAllStudent();

    Student getStudentById(Long id);

    void updateStudent(Long id, Student student);

    void deleteStudent(Long id);
}
