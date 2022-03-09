package com.dao;

import com.model.Company;
import com.model.Course;
import com.model.Group;
import com.service.CompanyService;
import com.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    private final CompanyService companyService;

    @Autowired
    public CourseDaoImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Course course, Long id) {
        course.setCompany(companyService.getCompanyById(id));
        entityManager.merge(course);
    }

    @Override
    public List<Course> getAllCourse(Long id) {
        return entityManager.createQuery("SELECT c FROM Course c where c.company.id =: id", Course.class).setParameter("id",id).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        Course course = entityManager.find(Course.class, id);
        if (course == null) {
            throw new EntityNotFoundException("Can't find Company for ID " + id);
        }
        return course;
    }

    @Override
    public void updateCourse(Long id, Course course) {
        Course course1 = getCourseById(id);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        entityManager.merge(course1);
    }




//    public void insertStudentAndCourseManyToMany( Student[] students, Course[] courses ) {
//        for ( Course course : courses ) {
//            em.persist( course );
//            for ( Student student : students ) {
//                student.addCourse( course ); em.persist( student );
//            }
//        }
//    }




        @Override
    public void deleteCourse(Long id) {
        entityManager.remove(getCourseById(id));
    }
}
