package com.learn.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author badrikant.soni on Nov,14/11/18,2018
 */

@Entity
@Table(name = "review")
@NoArgsConstructor
@Getter
@Setter
public class Review {

    // define fields

    // define constructors

    // define getter/setters

    // define tostring

    // annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    public Review(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", comment='" + comment + '\'' + '}';
    }
}
