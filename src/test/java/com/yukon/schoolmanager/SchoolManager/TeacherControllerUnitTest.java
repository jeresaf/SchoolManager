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
import com.yukon.schoolmanager.SchoolManager.controllers.TeacherController;
import com.yukon.schoolmanager.SchoolManager.entities.Teacher;
import com.yukon.schoolmanager.SchoolManager.repositories.TeacherRepository;

@WebMvcTest(TeacherController.class)
public class TeacherControllerUnitTest {

    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTeacher() throws Exception {
        
        Teacher teacher = new Teacher("Peter");

        mockMvc.perform(post("/api/v1/teachers").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(teacher)))
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void shouldReturnTacher() throws Exception {
        long id = 1L;
        Teacher teacher = new Teacher("Peter");
        teacher.setId(id);

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        mockMvc.perform(get("/api/v1/teachers/{id}", id)).andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value(teacher.getName()))
            .andDo(print());
    }

    @Test
    void shouldReturnNotFoundTeacher() throws Exception {
        long id = 1L;

        when(teacherRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/teachers/{id}", id))
            .andExpect(status().isNotFound())
            .andDo(print());
    }

    @Test
    void shouldReturnListOfTeachers() throws Exception {
        List<Teacher> teachers = new ArrayList<>(
            Arrays.asList(new Teacher("Peter"),
                new Teacher("Tigger"),
                new Teacher("Genie")));

        when(teacherRepository.findAll()).thenReturn(teachers);
        mockMvc.perform(get("/api/v1/teachers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(teachers.size()))
            .andDo(print());
    }

    @Test
    void shouldUpdateTeacher() throws Exception {
        long id = 1L;

        Teacher teacher = new Teacher("Genie");
        teacher.setId(id);
        Teacher updatedTeacher = new Teacher("Grogu");

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(updatedTeacher);

        mockMvc.perform(put("/api/v1/teachers/{id}", id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedTeacher)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(updatedTeacher.getName()))
            .andDo(print());
    }
  
    @Test
    void shouldReturnNotFoundUpdateTeacher() throws Exception {
        long id = 1L;

        Teacher updatedTeacher = new Teacher("Grogu");
        updatedTeacher.setId(id);

        when(teacherRepository.findById(id)).thenReturn(Optional.empty());
        when(teacherRepository.save(any(Teacher.class))).thenReturn(updatedTeacher);

        mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedTeacher)))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    
    @Test
    void shouldDeleteTeacher() throws Exception {
        long id = 1L;

        doNothing().when(teacherRepository).deleteById(id);
        mockMvc.perform(delete("/api/v1/teachers/{id}", id))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
    
    @Test
    void shouldDeleteAllTeacher() throws Exception {
        doNothing().when(teacherRepository).deleteAll();
        mockMvc.perform(delete("/api/v1/teachers"))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
    
}
