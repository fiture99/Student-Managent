package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/addStudent")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        studentService.updateStudent(studentId, student.getName(), student.getEmail(), student.getCourse(), student.getGrade());
    }

    @PostMapping("/{studentId}/course")
    public void addCourseToStudent(@PathVariable("studentId") Long studentId, @RequestBody Course course){
        studentService.addCourseToStudent(studentId,course, course.getGrade());
    }


    // Controller methods for handling views

    @Controller
    public class StudentViewController {

        @GetMapping("/students")
        public String getStudentsView(Model model) {
            List<Student> students = studentService.getStudents();
            model.addAttribute("students", students);
            return "students"; // assuming you have a Thymeleaf/HTML template named "students.html"
        }

        @GetMapping("/addStudent")
        public String showAddStudentForm(Model model) {
            model.addAttribute("student", new Student());
            return "addStudent"; // assuming you have a Thymeleaf/HTML template named "addStudent.html"
        }

    }
}
