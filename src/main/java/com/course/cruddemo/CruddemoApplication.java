package com.course.cruddemo;

import com.course.cruddemo.dao.AppDAO;
import com.course.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (AppDAO appDAO) {

		return runner -> {

			//createCourseAndStudent(appDAO);
			//findeCourseAndStudents(appDAO,10);
			//findStudentAndCourses(appDAO, 1);
			//addCoursesToStudent(appDAO, 1);

			deleteStudent(appDAO, 1);
		};
	}

	private void deleteStudent(AppDAO appDAO, int id) {

		appDAO.deleteStudent(id);
		System.out.println("done");
	}

	private void addCoursesToStudent(AppDAO appDAO, int id) {

		Student student = appDAO.findStudentAndCourses(id);

		Course course = new Course("for student");

		student.addCourse(course);

		appDAO.updateStudent(student);
	}

	private void findStudentAndCourses(AppDAO appDAO, int id) {

		System.out.println("The Student is " +
				appDAO.findStudentAndCourses(id));
		System.out.println("The Courses are " +
				appDAO.findStudentAndCourses(id).getCourses());

	}

	private void findeCourseAndStudents(AppDAO appDAO, int id) {

		System.out.println("The Course is " + appDAO.findCourseAndStudents(id));
		System.out.println("The Students are " + appDAO.findCourseAndStudents(id).getStudents());

	}

	private void createCourseAndStudent(AppDAO appDAO) {

		Course course = new Course("for Students3");
		Student student = new Student(	"Moustafa",
										"Sayed",
										"sayedmoustafa836@gmail.com");
		Student student1 = new Student(	"Moustafa",
				"Sayed",
				"sayedmoustafa836@gmail.com");
		course.addStudent(student1);
		course.addStudent(student);
		appDAO.save(course);
		System.out.println(course);
		System.out.println(course.getStudents());
	}

	private void retrieveCourseAndReviews(AppDAO appDAO, int id) {

		System.out.println("The Course is " + appDAO.findCourseAndReviews(id));
		System.out.println("The Reviews are " + appDAO.findCourseAndReviews(id).getReviews());
	}

	private void creatCourseAndReviews(AppDAO appDAO) {

		Course course = new Course("Frensh Course");

		course.addReview(new Review("the best course!"));
		course.addReview(new Review("Great one!"));

		appDAO.save(course);

		System.out.println(course);
		System.out.println(course.getReviews());
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO, int id) {
		System.out.println(appDAO.findInstructorJoinFetch(id));
		System.out.println(appDAO.findInstructorJoinFetch(id).getCourses());
	}

	private List<Course> findInstructorWithCourses(AppDAO appDAO, int id) {

		return appDAO.findCoursesByInstructorId(id);
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor instructor = new Instructor("Moustafa","Sayed",
				"sayedmoustafa836@gmail.com");
		InstructorDetails instructorDetails = new InstructorDetails("http://moustafa_sayed.com",
				"i love java");
		instructor.setInstructorDetails(instructorDetails);

		Course course1 = new Course("a");
		Course course2 = new Course("e");

		instructor.addCourse(course1);
		instructor.addCourse(course2);

		appDAO.save(instructor);

		System.out.println(instructor);
	}

	private void createInstructor (AppDAO appDAO) {

		Instructor instructor = new Instructor("Moustafa","Sayed",
				"sayedmoustafa836@gmail.com");
		InstructorDetails instructorDetails = new InstructorDetails("http://moustafa_sayed.com",
				"i love java");
		instructor.setInstructorDetails(instructorDetails);

		appDAO.save(instructor);

		System.out.println("the instructor " + instructor + " saved successfully");
	}

	private Instructor findInstructorById(AppDAO appDAO, int id) {

		return appDAO.findInstructorById(id);
	}

	private List<Course> findInstructorCourses(AppDAO appDAO, int id) {

		return appDAO.findInstructorById(id).getCourses();
	}

	private void deleteInstructorById(AppDAO appDAO, int id) {

		appDAO.deleteInstructorById(id);
		System.out.println("instructor deleted successfully !");
	}

	private InstructorDetails findInstructorDetailsById(AppDAO appDAO, int id) {

		return appDAO.findInstructorDetailsById(id);
	}

	private void updateInstructor(AppDAO appDAO, int id) {

		Instructor instructor = findInstructorById(appDAO, id);
		instructor.setFirstName("Moustafa");
		appDAO.updateInstructor(instructor);

	}

	private void updateCourse(AppDAO appDAO, int id) {

		Course course =  appDAO.findCourseById(id);
		course.setTitle("english");
		appDAO.updateCourse(course);

	}

	private void deleteInstructor(AppDAO appDAO, int id) {

		appDAO.findCoursesByInstructorId(id);
	}

	private void deleteCourseById(AppDAO appDAO, int id){

		appDAO.deleteCourse(id);
		System.out.println("Done");
	}

}
