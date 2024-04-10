package com.demo.spring.data.jpa.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Long courseId;

    private String title;

    private Integer credit;

    @OneToOne(
            mappedBy = "course"  //  Bi - directional Mapping
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )

    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn( //for student what should be the course
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(//for courses which student should be mapped
                    name = "student_id",
                    referencedColumnName = "student_id" // as i have used @Column annotation to create the column i need to specify that column name instead of the attribute name in entity class.
            )

    ) // which table,joined table,which particular column we are going to refer here
    private List<Student> students;

    public void addStudents(Student student) {
        if (students == null) students = new ArrayList<>();
        students.add(student);
    }

}
