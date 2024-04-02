package com.demo.spring.data.jpa.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder // to add builder patter for my class
@Table(
        name = "tbl_student",
        uniqueConstraints = @UniqueConstraint( // email id in the table should be unique
                name = "emailid_unique",
                columnNames = "email_address"
        )
) // table name of the entity in db
public class Student {

    @Id // primary key annotation
    @SequenceGenerator( // how seq should be generated
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    //how the value will be generated
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address", nullable = false) // to define column name for the attribute in the table
    private String emailId;

    @Embedded
    private Guardian guardian;
}
