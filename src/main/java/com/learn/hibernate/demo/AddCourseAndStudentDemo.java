package com.learn.hibernate.demo;

import com.learn.hibernate.entity.Course;
import com.learn.hibernate.entity.Instructor;
import com.learn.hibernate.entity.InstructorDetail;
import com.learn.hibernate.entity.Review;
import com.learn.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author badrikant.soni on Nov,14/11/18,2018
 *
 * Add more courses to student
 */
public class AddCourseAndStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .addAnnotatedClass(Review.class)
                                                    .addAnnotatedClass(Student.class)
                                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the student Badrikant from database
            int studentId = 1;
            Student theStudent = session.get(Student.class,studentId);

            System.out.println("\nLoaded student: " + theStudent);
            System.out.println("Courses: " + theStudent.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's cube - How to speed cube");
            Course tempCourse2 = new Course("Mario - Game Development");


            // add student to courses
            tempCourse1.addStudent(theStudent);
            tempCourse2.addStudent(theStudent);

            // save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {

            // add clean up code
            session.close();

            factory.close();
        }

    }
}
