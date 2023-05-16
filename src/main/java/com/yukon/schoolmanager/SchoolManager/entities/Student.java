package com.yukon.schoolmanager.SchoolManager.entities;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    Student() {}

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
  
      if (this == o)
        return true;
      if (!(o instanceof Student))
        return false;
      Student student = (Student) o;
      return Objects.equals(this.id, student.id) && Objects.equals(this.name, student.name)
          && Objects.equals(this.surname, student.surname);
    }
  
    @Override
    public int hashCode() {
      return Objects.hash(this.id, this.name, this.surname);
    }
  
    @Override
    public String toString() {
      return "Student{" + "id=" + this.id + ", name='" + this.name + '\'' + ", surname='" + this.surname + '\'' + '}';
    }
    
}
