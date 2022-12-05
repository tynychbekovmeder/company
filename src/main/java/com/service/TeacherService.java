package com.service;

import com.model.Teacher;

import java.util.List;

public interface TeacherService {

    void save(Teacher teacher, Long id);

    List<Teacher> getAllTeacher();

    Teacher getTeacherById(Long id);

    void updateTeacher(Long id, Teacher teacher);

    void deleteTeacher(Long id);
}
