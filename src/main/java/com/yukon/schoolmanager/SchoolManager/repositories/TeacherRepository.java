package com.yukon.schoolmanager.SchoolManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yukon.schoolmanager.SchoolManager.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
}
