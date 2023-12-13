package com.course.cruddemo.dao;

import com.course.cruddemo.entity.Course;
import com.course.cruddemo.entity.Instructor;
import com.course.cruddemo.entity.InstructorDetails;
import com.course.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDAO {

    private EntityManager entityManager;

    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        entityManager.remove(findInstructorById(id));
    }

    @Override
    public InstructorDetails findInstructorDetailsById(int id) {
        return entityManager.find(InstructorDetails.class, id);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );

        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorJoinFetch(int id) {

        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "where i.id = :data", Instructor.class
        );

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {

        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {

        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {

        Instructor instructor = findInstructorById(id);
        List<Course> courses = findCoursesByInstructorId(id);
        for (Course c : courses) {
            c.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void save(Course course) {

        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviews(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class
        );

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {

        Course course = findCourseById(id);
        entityManager.remove(course);

    }

    @Override
    public Course findCourseAndStudents(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class
        );

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCourses(int id) {

        TypedQuery<Student> query = entityManager.createQuery(
                "select c from Student c "
                        + "JOIN FETCH c.courses "
                        + "where c.id = :data", Student.class
        );

        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {

        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {

        entityManager.remove(findStudentAndCourses(id));
    }
}
