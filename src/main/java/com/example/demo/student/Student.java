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

    private String field;
    private Integer semester;
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
                   String field,
                   Integer semester,
                    String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.field = field;
        this.semester = semester;
        this.status = status;


    }



    public Student(String name,
                   String email,
                   LocalDate dob,
                   String field,
                   Integer semester,
                   String status) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.field = field;
        this.semester = semester;
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


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getSemester() {
        return  semester;
    }

// public String getLetterGrade(){
//
//        if(letterGrade == null){
//            letterGrade = LetterGrade.calculateGarde(grade);
//        }
//        return  letterGrade.getLabel();
// }
    public void setSemester(Integer semester) {
        this.semester = semester;
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
                ", field" + field +
                ", semester" + semester +
                '}';
    }
}
