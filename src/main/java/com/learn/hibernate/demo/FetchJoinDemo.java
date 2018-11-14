package com.learn.hibernate.demo;

import com.learn.hibernate.entity.Course;
import com.learn.hibernate.entity.Instructor;
import com.learn.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * @author badrikant.soni on Nov,14/11/18,2018
 *
 * Another solution to prevent lazy initialization exeception when hibernate session is closed.
 */
public class FetchJoinDemo {

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

            // option 2: Hibernate query with HQL

            // get the instructor from db
            int theId = 1;

            Query<Instructor> query = session.createQuery(
                    "select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id=:theInstructorId",
                    Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("Fetch: Instructor: " + tempInstructor);

            // commit transaction
            session.getTransaction().commit();

            // close the session
            session.close();

            System.out.println("\nThe session is now closed!\n");

            // get courses for the instructor
            System.out.println("Fetch: Courses: " + tempInstructor.getCourses());

            System.out.println("Fetch: Done!");
        } finally {

            // add clean up code
            session.close();

            factory.close();
        }
    }
}
