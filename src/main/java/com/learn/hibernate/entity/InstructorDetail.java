package com.learn.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author badrikant.soni on Nov,2018
 */

@NoArgsConstructor
@Entity
@Table(name = "instructor_detail")
@Getter
@Setter
public class InstructorDetail {

    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db column names

    // create constructors

    // generate getter/setter methods

    // generate toString() method

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    // add new field for instructor (also add getter/setters)

    // add @OneToOne annotation

    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Instructor instructor;

    @Override
    public String toString() {
        return "InstructorDetail{" + "id=" + id + ", youtubeChannel='" + youtubeChannel + '\'' + ", hobby='" + hobby
                + '\'' + '}';
    }
}
