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

import com.yukon.schoolmanager.SchoolManager.entities.Teacher;
import com.yukon.schoolmanager.SchoolManager.exceptions.ResourceNotFoundException;
import com.yukon.schoolmanager.SchoolManager.repositories.TeacherRepository;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/teachers")
    ResponseEntity<List<Teacher>> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
      
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
    // end::get-aggregate-root[]

    @PostMapping("/teachers")
    ResponseEntity<Teacher> newTeacher(@RequestBody Teacher teacher) {
        Teacher _teacher = teacherRepository.save(new Teacher(teacher.getName()));
        return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
    }

    // Single item
  
    @GetMapping("/teachers/{id}")
    ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
        
        Teacher teacher = teacherRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Failed to find teacher with id = " + id));

        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PutMapping("/teachers/{id}")
    ResponseEntity<Teacher> replaceteacher(@RequestBody Teacher teacher, @PathVariable Long id) {

        Teacher _teacher = teacherRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Failed to find teacher with id = " + id));

        _teacher.setName(teacher.getName());

        return new ResponseEntity<>(teacherRepository.save(_teacher), HttpStatus.OK);
        
    }

    @DeleteMapping("/teachers/{id}")
    ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long id) {
        teacherRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/teachers")
    ResponseEntity<HttpStatus> deleteAllTeachers() {
        teacherRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
