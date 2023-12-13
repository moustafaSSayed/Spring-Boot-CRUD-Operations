package com.course.cruddemo.dao;

import com.course.cruddemo.entity.Course;
import com.course.cruddemo.entity.Instructor;
import com.course.cruddemo.entity.InstructorDetails;
import com.course.cruddemo.entity.Student;

import java.util.List;


public interface AppDAO {
    public void save(Instructor instructor);
    Instructor findInstructorById (int id);
    public void deleteInstructorById(int id);

    public InstructorDetails findInstructorDetailsById(int id);

    List<Course> findCoursesByInstructorId(int id);

    public Instructor findInstructorJoinFetch(int id);

    public void updateInstructor(Instructor instructor);

    public Course findCourseById(int id);

    public void updateCourse(Course course);

    public void deleteInstructor(int id);

    public void save(Course course);

    public Course findCourseAndReviews(int id);

    public void deleteCourse(int id);

    public Course findCourseAndStudents(int id);

    public Student findStudentAndCourses(int id);

    public void updateStudent(Student student);

    public void deleteStudent(int id);
}
