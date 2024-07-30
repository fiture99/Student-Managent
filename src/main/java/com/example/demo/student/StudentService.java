package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
//import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email is already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with ID " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email, String course, Integer semester) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with ID " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken");
            }
            student.setEmail(email);
        }

        if (semester != null && semester > 0 && semester <= 6) {
            student.setSemester(semester);
        }

        student.setField(course);
    }

    @Transactional
    public void addCourseToStudent(Long studentId, Long id, Integer courseCode, Course course, Integer grade){
        Student student =  studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with ID "+studentId+" does not exist"));
            if (grade != null && grade > 0 && !Objects.equals(course.getLetterGrade(), grade)) {
            course.setGrade(grade);
            }
        if (courseCode != null && courseCode > 0 && Objects.equals(course.getCourseCode(), courseCode)) {
            Optional<Course> courseOptional = courseRepository.findCourseByCourseCode(courseCode);
            if (courseOptional.isPresent()) {
                throw new IllegalStateException("Course is already added "+ courseCode+ "for "+ studentId);
            }
            course.setCourseCode(courseCode);
        }

        student.getCourses().add(course);
        studentRepository.save(student);

    }
}
