package com.learn.hibernate.demo;

import com.learn.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author badrikant.soni on Nov,2018
 */
public class StudentDemo {

    public static void main(String[] args) {

        // create a session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            System.out.println("Creating a new Student object...");

            // create a Student objecy
            Student theStudent = new Student("Badrikant", "Soni", "abc@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student..");
            session.save(theStudent);

            // commit transaction
            session.getTransaction().commit();

        } finally {

            factory.close();
        }

    }
}
