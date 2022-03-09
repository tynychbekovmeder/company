package com.service;

import com.dao.CourseDao;
import com.model.Company;
import com.model.Course;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Override
    public void save(Course course, Long id) {
        courseDao.save(course, id);
    }

    @Override
    public List<Course> getAllCourse(Long id) {
        return courseDao.getAllCourse(id);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        courseDao.updateCourse(id, course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseDao.deleteCourse(id);
    }
}
