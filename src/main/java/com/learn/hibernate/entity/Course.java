package com.learn.hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author badrikant.soni on Nov,14/11/18,2018
 */

@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course {

    // define our fields

    // define constructors

    // define getter setters

    // define tostring

    // annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Course(String title) {
        this.title = title;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title='" + title + '\'' + '}';
    }

    public void addReview(Review theReview) {
        if (reviews == null) {
            reviews = new ArrayList<Review>();
        }
        reviews.add(theReview);

    }

    // add a convenience method
    public void addStudent(Student theStudent) {
        if (students == null) {
            students = new ArrayList<Student>();
        }
        students.add(theStudent);
    }
}
