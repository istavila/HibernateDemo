package com.hibernate.model.jointable;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
    @SequenceGenerator(name= "employee_generator", sequenceName = "employee_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_name")
    private String name;

    private Integer salary;

    @ManyToMany
    @JoinTable(name = "employees_companies",
            joinColumns = @JoinColumn(table = "employees", name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(table = "companies", name = "company_id", referencedColumnName = "id"))
    private Set<Company> companies;

}
