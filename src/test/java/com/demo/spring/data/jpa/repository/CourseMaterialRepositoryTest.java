package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.persistence.entity.Course;
import com.demo.spring.data.jpa.persistence.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {

        Course course =
                Course.builder()
                        .title(".net")
                        .credit(6)
                        .build();
        CourseMaterial courseMaterial = // we are trying to persist cou
                CourseMaterial.builder()
                        .url("www.javatpoint.com")
                        .course(course)
                        //.course(course) it is forcing us to add course if commented gives the below error
                        // org.springframework.dao.DataIntegrityViolationException: not-null property references a null or transient value : com.demo.spring.data.jpa.persistence.entity.CourseMaterial.course
                        .build();

        courseMaterialRepository.save(courseMaterial);
    }
    // we get -> org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.demo.spring.data.jpa.persistence.entity.CourseMaterial.course -> com.demo.spring.data.jpa.persistence.entity.Course
    // because we have not saved any course yet
    // For this we use Cascade types

    @Test
    public void printAllCourseMaterialsTest(){
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();

        System.out.println("CourseMaterials =" + courseMaterials);
    }
}