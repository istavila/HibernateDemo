package com.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_generator")
    @SequenceGenerator(name = "student_id_generator", sequenceName = "student_sequence")
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_age")
    private int studentAge;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}