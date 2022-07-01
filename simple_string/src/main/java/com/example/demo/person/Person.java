package com.example.demo.person;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "people")
public class Person {

    @Id
    @SequenceGenerator(
            name = "people_sequence",
            sequenceName = "people_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "people_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    @Transient
    private Integer age;

    public Person(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
