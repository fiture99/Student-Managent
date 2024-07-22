package com.example.demo.student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends  JpaRepository <Course, Long>{

    Optional<Course> findCourseByCourseCode(Integer courseCode);
}
