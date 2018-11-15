package com.learn.hibernate.demo;

import com.learn.hibernate.entity.Course;
import com.learn.hibernate.entity.Instructor;
import com.learn.hibernate.entity.InstructorDetail;
import com.learn.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author badrikant.soni on Nov,14/11/18,2018
 *
 * Create courses and add reviews.
 */
public class CreateCourseAndReviewsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .addAnnotatedClass(Review.class)
                                                    .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // create a new course
            Course tempCourse = new Course("Pacman - how to Score one million points");

            // add some reviews
            tempCourse.addReview(new Review("Great Course..loved it"));
            tempCourse.addReview(new Review("Cool Course..loved it"));
            tempCourse.addReview(new Review("What a dump course..."));

            // save the course ... and leverage the cascade all
            System.out.println("saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);

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
