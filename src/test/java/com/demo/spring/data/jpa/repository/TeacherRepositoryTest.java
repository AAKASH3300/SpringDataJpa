package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.persistence.entity.Course;
import com.demo.spring.data.jpa.persistence.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacherTest() {

        Course course1 =
                Course.builder()
                        .title("ST")
                        .credit(5)
                        .build();
        Course course2 =
                Course.builder()
                        .title("DAA")
                        .credit(5)
                        .build();
        Teacher teacher =
                Teacher.builder()
                        .firstName("Kayal")
                        .lastName("Vizhi")
                        //.course(List.of(course1, course2))
                        .build();
        teacherRepository.save(teacher);
    }

}