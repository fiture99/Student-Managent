package com.example.demo.student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1

    )

    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private  Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    private String course;
    private Integer grade;
    private LetterGrade letterGrade;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private List<Course> courses =  new ArrayList<>();




    public Student() {
    }

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dob,
                   String course,
                   Integer grade,
                    String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.course = course;
        this.grade = grade;
        this.status = status;


    }



    public Student(String name,
                   String email,
                   LocalDate dob,
                   String course,
                   Integer grade,
                   String status) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.course = course;
        this.grade = grade;
        this.status = status;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", course" + course +
                ", grade" + grade +
                '}';
    }
}
