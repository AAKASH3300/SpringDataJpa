package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.persistence.entity.Course;
import com.demo.spring.data.jpa.persistence.entity.Student;
import com.demo.spring.data.jpa.persistence.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courseList =
                courseRepository.findAll();
        System.out.println("course =" + courseList);

    }

    @Test
    public void saveCourseWithTeacherObject() {
        Teacher teacher = Teacher.builder()
                .firstName("Priya")
                .lastName("Mani")
                .build();

        Course course = Course
                .builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords) // secondPageWithTwoRecords
                        .getContent();

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords) // secondPageWithTwoRecords
                        .getTotalElements();

        long totalPages =
                courseRepository.findAll(firstPageWithThreeRecords) // secondPageWithTwoRecords
                        .getTotalPages();

        System.out.println("totalPages = " + totalPages);

        System.out.println("totalElements = " + totalElements);

        System.out.println("Courses = " + courses);
    }

    @Test
    public void findAllWithSorting() {
        Pageable sortByTitle =
                PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void printFindByTitleContainingTest() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        List<Course> courseList =
                courseRepository.findByTitleContaining("D", firstPageTenRecords).getContent();

        System.out.println("courseList = " + courseList);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher
                .builder()
                .firstName("Swathi")
                .lastName("Priyadharshini")
                .build();

        Student student = Student.builder()
                .firstName("Brindha")
                .lastName("S")
                .emailId("brindha@gmail.com")
                .build();

        Course course = Course
                .builder()
                .title("AIML")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}