package com.example.demo.student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Integer grade;
    private LetterGrade letterGrade;
    public Course() {}

    public Course(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return  grade;
    }

    public String getLetterGrade(){

        if(letterGrade == null){
            letterGrade = LetterGrade.calculateGarde(grade);
        }
        return  letterGrade.getLabel();
    }
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
