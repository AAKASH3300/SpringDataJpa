package com.demo.spring.data.jpa.repository;

import com.demo.spring.data.jpa.persistence.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    //Custom Method for Sorting
    Page<Course> findByTitleContaining(String title, Pageable pageRequest);
}
