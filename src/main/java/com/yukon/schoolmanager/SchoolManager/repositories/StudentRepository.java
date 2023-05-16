package com.yukon.schoolmanager.SchoolManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yukon.schoolmanager.SchoolManager.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
