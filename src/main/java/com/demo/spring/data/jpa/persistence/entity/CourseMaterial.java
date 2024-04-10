package com.demo.spring.data.jpa.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude ="course")
public class  CourseMaterial {

    @Id
    @SequenceGenerator(name = "material_sequence",sequenceName = "material_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "material_sequence")
    private Long courseMaterialId;

    private String url;

    //Define Mapping
    @OneToOne(   // Uni - directional mapping
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name="course_id",referencedColumnName = "courseId") // to specify which column the key is referencing
    private Course course;
}
