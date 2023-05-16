package com.yukon.schoolmanager.SchoolManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.schoolmanager.SchoolManager.controllers.StudentController;
import com.yukon.schoolmanager.SchoolManager.entities.Student;
import com.yukon.schoolmanager.SchoolManager.repositories.StudentRepository;

@WebMvcTest(StudentController.class)
public class StudentControllerUnitTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateStudent() throws Exception {
        
        Student student = new Student("Donald", "Duck");

        mockMvc.perform(post("/api/v1/students").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(student)))
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void shouldReturnStudent() throws Exception {
        long id = 1L;
        Student student = new Student("Donald", "Duck");
        student.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        mockMvc.perform(get("/api/v1/students/{id}", id)).andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value(student.getName()))
            .andExpect(jsonPath("$.surname").value(student.getSurname()))
            .andDo(print());
    }

    @Test
    void shouldReturnNotFoundStudent() throws Exception {
        long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/students/{id}", id))
            .andExpect(status().isNotFound())
            .andDo(print());
    }

    @Test
    void shouldReturnListOfStudents() throws Exception {
        List<Student> students = new ArrayList<>(
            Arrays.asList(new Student("Donald", "Duck"),
                new Student("Captain", "Hook"),
                new Student("James", "Sullivan")));

        when(studentRepository.findAll()).thenReturn(students);
        mockMvc.perform(get("/api/v1/students"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(students.size()))
            .andDo(print());
    }

    @Test
    void shouldUpdateStudent() throws Exception {
        long id = 1L;

        Student student = new Student("James", "Sullivan");
        student.setId(id);
        Student updatedStudent = new Student("Lightning", "McQueen");

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/api/v1/students/{id}", id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedStudent)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(updatedStudent.getName()))
            .andDo(print());
    }
  
    @Test
    void shouldReturnNotFoundUpdateStudent() throws Exception {
        long id = 1L;

        Student updatedStudent = new Student("Lightning", "McQueen");
        updatedStudent.setId(id);

        when(studentRepository.findById(id)).thenReturn(Optional.empty());
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedStudent)))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    
    @Test
    void shouldDeleteStudent() throws Exception {
        long id = 1L;

        doNothing().when(studentRepository).deleteById(id);
        mockMvc.perform(delete("/api/v1/students/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
    
    @Test
    void shouldDeleteAllStudent() throws Exception {
        doNothing().when(studentRepository).deleteAll();
        mockMvc.perform(delete("/api/v1/students"))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
    
}
