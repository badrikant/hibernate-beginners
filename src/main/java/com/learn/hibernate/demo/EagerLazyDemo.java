package com.learn.hibernate.demo;

import com.learn.hibernate.entity.Course;
import com.learn.hibernate.entity.Instructor;
import com.learn.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author badrikant.soni on Nov,14/11/18,2018
 *
 * Retrieve instructor & courses using Eager & Lazy loading
 * FetchType.Eager - Load both instructor and course at one shot from DB and keep them in memory.
 * FetchType.Lazy - Load instructor initially and load course when requested.
 */
public class EagerLazyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Fetch : Instructor: " + tempInstructor);

            // get courses for the instructor
            System.out.println("Fetch : Courses: " + tempInstructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\n This is seession is closed.");

            // option 1: call getter method while session is open

            // get courses for the instructor
            System.out.println("Fetch : Courses: " + tempInstructor.getCourses());

            System.out.println("Done!");
        } finally {

            // add clean up code
            session.close();

            factory.close();
        }
    }

}
