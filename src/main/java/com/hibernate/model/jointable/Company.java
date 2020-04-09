package com.hibernate.model.jointable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_generator")
    @SequenceGenerator(name = "company_generator", sequenceName = "company_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name")
    private String name;

    @ManyToMany(mappedBy = "companies")
    private Set<Employee> employees;
}
