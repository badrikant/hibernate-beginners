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
 * Create courses and add reviews.
 */
public class CreateCourseAndStudentDemo {

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

            // create a new course
            Course tempCourse = new Course("Pacman - how to Score one million points");

            // save the course
            System.out.println("\nSaving the course ...");
            session.save(tempCourse);
            System.out.println("Saved course:" + tempCourse);

            // create the students
            Student tempStudent1 = new Student("Badrikant","Soni","abc@gmail.com");
            Student tempStudent2 = new Student("Shri","D","xyz@gmail.com");


            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            System.out.println("\nSaving students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students" + tempCourse);

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
