package com.yukon.schoolmanager.SchoolManager.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukon.schoolmanager.SchoolManager.entities.Student;
import com.yukon.schoolmanager.SchoolManager.exceptions.ResourceNotFoundException;
import com.yukon.schoolmanager.SchoolManager.repositories.StudentRepository;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentRepository studentRepository;

    StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/students")
    ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
      
        return new ResponseEntity<>(students, HttpStatus.OK);

    }
    // end::get-aggregate-root[]

    @PostMapping("/students")
    ResponseEntity<Student> newStudent(@RequestBody Student student) {
        Student _student = studentRepository.save(new Student(student.getName(), student.getSurname()));
        return new ResponseEntity<>(_student, HttpStatus.CREATED);

    }

    // Single item
  
    @GetMapping("/students/{id}")
    ResponseEntity<Student> getStudent(@PathVariable Long id) {
        
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Failed to find student with id = " + id));

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {

        Student _student = studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Failed to find student with id = " + id));

        _student.setName(student.getName());
        _student.setSurname(student.getSurname());

        return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/students")
    ResponseEntity<HttpStatus> deleteAllStudents() {
        studentRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
