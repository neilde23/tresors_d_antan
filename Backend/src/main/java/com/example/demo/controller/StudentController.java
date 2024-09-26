//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/students"})
public class StudentController {
    @Autowired
    private StudentService studentService;

    public StudentController() {
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return this.studentService.getAllStudents();
    }

    @GetMapping({"/{id}"})
    public Student getStudentById(@PathVariable Long id) {
        return this.studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.createStudent(student);
    }

    @PutMapping({"/{id}"})
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return this.studentService.updateStudent(id, studentDetails);
    }

    @DeleteMapping({"/{id}"})
    public void deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
    }
}
