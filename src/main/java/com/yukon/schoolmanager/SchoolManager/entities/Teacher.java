package com.yukon.schoolmanager.SchoolManager.entities;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(name = "teacher_students",
          joinColumns = { @JoinColumn(name = "teacher_id") },
          inverseJoinColumns = { @JoinColumn(name = "student_id") })
    private Set<Student> students = new HashSet<>();

    Teacher() {}

    public Teacher(String name) {
        this.name = name;
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

    public void addStudent(Student student) {
      this.students.add(student);
      //student.getTeachers().add(this);
    }
    
    public void removeStudent(long studentId) {
      Student student = this.students.stream().filter(s -> s.getId() == studentId).findFirst().orElse(null);
      if (student != null) {
        this.students.remove(student);
        //student.geTeachers().remove(this);
      }
    }

    public List<Student> getTeacherStudents(){
        return new ArrayList<Student> (students);
    }

    @Override
    public boolean equals(Object o) {
  
      if (this == o)
        return true;
      if (!(o instanceof Teacher))
        return false;
      Teacher teacher = (Teacher) o;
      return Objects.equals(this.id, teacher.id) && Objects.equals(this.name, teacher.name);
    }
  
    @Override
    public int hashCode() {
      return Objects.hash(this.id, this.name);
    }
  
    @Override
    public String toString() {
      return "Teacher{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
    
}
