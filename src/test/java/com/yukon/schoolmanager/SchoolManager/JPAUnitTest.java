package com.yukon.schoolmanager.SchoolManager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.yukon.schoolmanager.SchoolManager.entities.Student;
import com.yukon.schoolmanager.SchoolManager.entities.Teacher;
import com.yukon.schoolmanager.SchoolManager.repositories.StudentRepository;
import com.yukon.schoolmanager.SchoolManager.repositories.TeacherRepository;

@DataJpaTest
public class JPAUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void should_find_no_teachers_if_repository_is_empty() {
        Iterable<Teacher> teachers = teacherRepository.findAll();
        assertThat(teachers).isEmpty();
    }

    @Test
    public void should_store_a_teacher() {
        Teacher teacher = teacherRepository.save(new Teacher("Peter"));
        assertThat(teacher).hasFieldOrPropertyWithValue("name", "Peter");
    }

    @Test
    public void should_find_all_teachers() {
        Teacher teacher1 = new Teacher("Peter");
        entityManager.persist(teacher1);

        Teacher teacher2 = new Teacher("Tigger");
        entityManager.persist(teacher2);

        Teacher teacher3 = new Teacher("Genie");
        entityManager.persist(teacher3);

        Iterable<Teacher> teachers = teacherRepository.findAll();

        assertThat(teachers).hasSize(3).contains(teacher1, teacher2, teacher3);
    }

    @Test
    public void should_find_a_teacher_by_id() {
        Teacher teacher1 = new Teacher("Peter");
        entityManager.persist(teacher1);

        Teacher teacher2 = new Teacher("Tigger");
        entityManager.persist(teacher2);

        Teacher teacher3 = new Teacher("Genie");
        entityManager.persist(teacher3);

        Teacher foundTeacher = teacherRepository.findById(teacher2.getId()).get();

        assertThat(foundTeacher).isEqualTo(teacher2);
    }

    @Test
    public void should_update_a_teacher_by_id() {
        Teacher teacher1 = new Teacher("Peter");
        entityManager.persist(teacher1);

        Teacher teacher2 = new Teacher("Tigger");
        entityManager.persist(teacher2);

        Teacher teacher3 = new Teacher("Genie");
        entityManager.persist(teacher3);

        Teacher updatedTeacher = new Teacher("Grogu");
        entityManager.persist(updatedTeacher);

        Teacher teacher = teacherRepository.findById(teacher2.getId()).get();
        teacher.setName(updatedTeacher.getName());
        teacherRepository.save(teacher);

        Teacher checkTeacher = teacherRepository.findById(teacher2.getId()).get();
        
        assertThat(checkTeacher.getId()).isEqualTo(teacher2.getId());
        assertThat(checkTeacher.getName()).isEqualTo(updatedTeacher.getName());
    }

    @Test
    public void should_delete_a_teacher_by_id() {
        Teacher teacher1 = new Teacher("Peter");
        entityManager.persist(teacher1);

        Teacher teacher2 = new Teacher("Tigger");
        entityManager.persist(teacher2);

        Teacher teacher3 = new Teacher("Genie");
        entityManager.persist(teacher3);

        teacherRepository.deleteById(teacher2.getId());

        Iterable<Teacher> teachers = teacherRepository.findAll();

        assertThat(teachers).hasSize(2).contains(teacher1, teacher3);

    }

    @Test
    public void should_delete_all_teachers() {
        Teacher teacher1 = new Teacher("Peter");
        entityManager.persist(teacher1);

        Teacher teacher2 = new Teacher("Tigger");
        entityManager.persist(teacher2);

        Teacher teacher3 = new Teacher("Genie");
        entityManager.persist(teacher3);

        teacherRepository.deleteAll();

        assertThat(teacherRepository.findAll()).isEmpty();

    }

    /**
     * Students
     */

    @Test
    public void should_find_no_students_if_repository_is_empty() {
        Iterable<Student> students = studentRepository.findAll();
        assertThat(students).isEmpty();
    }

    @Test
    public void should_store_a_student() {
        Student student = studentRepository.save(new Student("Jiminy", "Cricket"));
        assertThat(student).hasFieldOrPropertyWithValue("name", "Jiminy");
        assertThat(student).hasFieldOrPropertyWithValue("surname", "Cricket");
    }

    @Test
    public void should_find_all_students() {
        Student student1 = new Student("Donald", "Duck");
        entityManager.persist(student1);

        Student student2 = new Student("Captain", "Hook");
        entityManager.persist(student2);

        Student student3 = new Student("James", "Sullivan");
        entityManager.persist(student3);

        Iterable<Student> students = studentRepository.findAll();

        assertThat(students).hasSize(3).contains(student1, student2, student3);
    }

    @Test
    public void should_find_a_student_by_id() {
        Student student1 = new Student("Donald", "Duck");
        entityManager.persist(student1);

        Student student2 = new Student("Captain", "Hook");
        entityManager.persist(student2);

        Student student3 = new Student("James", "Sullivan");
        entityManager.persist(student3);

        Student foundStudent = studentRepository.findById(student2.getId()).get();

        assertThat(foundStudent).isEqualTo(student2);
    }

    @Test
    public void should_update_a_student_by_id() {
        Student student1 = new Student("Donald", "Duck");
        entityManager.persist(student1);

        Student student2 = new Student("Captain", "Hook");
        entityManager.persist(student2);

        Student student3 = new Student("James", "Sullivan");
        entityManager.persist(student3);

        Student updatedStudent = new Student("Lightning", "McQueen");
        entityManager.persist(updatedStudent);

        Student student = studentRepository.findById(student2.getId()).get();
        student.setName(updatedStudent.getName());
        student.setSurname(updatedStudent.getSurname());
        studentRepository.save(student);

        Student checkStudent = studentRepository.findById(student2.getId()).get();
        
        assertThat(checkStudent.getId()).isEqualTo(student2.getId());
        assertThat(checkStudent.getName()).isEqualTo(updatedStudent.getName());
        assertThat(checkStudent.getSurname()).isEqualTo(updatedStudent.getSurname());
    }

    @Test
    public void should_delete_a_student_by_id() {
        Student student1 = new Student("Donald", "Duck");
        entityManager.persist(student1);

        Student student2 = new Student("Captain", "Hook");
        entityManager.persist(student2);

        Student student3 = new Student("James", "Sullivan");
        entityManager.persist(student3);

        studentRepository.deleteById(student2.getId());

        Iterable<Student> students = studentRepository.findAll();

        assertThat(students).hasSize(2).contains(student1, student3);
    }

    @Test
    public void should_delete_all_students() {
        Student student1 = new Student("Donald", "Duck");
        entityManager.persist(student1);

        Student student2 = new Student("Captain", "Hook");
        entityManager.persist(student2);

        Student student3 = new Student("James", "Sullivan");
        entityManager.persist(student3);

        studentRepository.deleteAll();

        assertThat(studentRepository.findAll()).isEmpty();
    }
    
}
