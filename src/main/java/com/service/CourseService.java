package com.service;

import com.model.Course;

import java.util.List;

public interface CourseService {

    void save(Course course, Long id);

    List<Course> getAllCourse(Long id);

    List<Course> findAll();

    Course getCourseById(Long id);

    void updateCourse(Long id, Course course);

    void deleteCourse(Long id);
}
