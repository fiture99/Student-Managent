package com.example.demo.student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends  JpaRepository <Course, Long>{

}