package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(path = "/api/v1/student")
public class StudentController {
    private final  StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = studentService.getStudents();

        // Group by course
//        Map<String, List<Student>> studentsByCourse = students.stream()
//                        .collect(Collectors.groupingBy(Student::getCourse));
//        model.addAttribute("studentsByCourse", studentsByCourse);
        model.addAttribute("students", students);

        return "students";
    }

    @GetMapping("/addStudent")
    public String showRegistrationFrom(Model model){
        model.addAttribute("student", new Student());
        return "addStudent";
    }
    @PostMapping("/addStudent")
    public String registerNewStudent(@ModelAttribute("student") Student student){
        studentService.addNewStudent(student);
        return "redirect:/api/v1/student/students";
    }

    @PostMapping( path= "/delete/{studentId}")
    public String  deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/api/v1/student/students";

    }

    @PutMapping(path = "/update/{studentId}")
    public String updateStudent(
            @PathVariable Long studentId,
            @RequestParam String name,
            @RequestParam  String email,
            @RequestParam  String course,
            @RequestParam Integer grade){
        studentService.updateStudent(studentId, name, email, course,grade);
        return "redirect:/api/v1/student/students";
    }


}
