//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService() {
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return (Student)this.studentRepository.findById(id).orElse((Student) null);
    }

    public Student createStudent(Student student) {
        return (Student)this.studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        this.studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = (Student)this.studentRepository.findById(id).orElse((Student) null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setAge(student.getAge());
            return (Student)this.studentRepository.save(existingStudent);
        } else {
            return null;
        }
    }
}
