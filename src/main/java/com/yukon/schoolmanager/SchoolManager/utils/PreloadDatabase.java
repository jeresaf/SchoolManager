package com.yukon.schoolmanager.SchoolManager.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yukon.schoolmanager.SchoolManager.entities.Student;
import com.yukon.schoolmanager.SchoolManager.entities.Teacher;
import com.yukon.schoolmanager.SchoolManager.repositories.StudentRepository;
import com.yukon.schoolmanager.SchoolManager.repositories.TeacherRepository;

@Configuration
public class PreloadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(PreloadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository, TeacherRepository teacherRepository) {
  
      return args -> {
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        Student s1 = studentRepository.save(new Student("Naruto", "Uzumaki"));
        Student s2 = studentRepository.save(new Student("Sasuke", "Uchiha"));
        Student s3 = studentRepository.save(new Student("Sakura", "Haruno"));
        Student s4 = studentRepository.save(new Student("Hinata", "Hyuga"));
        Teacher t1 = new Teacher("Madara Uchiha");
        Teacher t2 = new Teacher("Griffith");
        log.info("Preloading " + s1);
        log.info("Preloading " + s2);
        log.info("Preloading " + s3);
        log.info("Preloading " + s4);
        log.info("Preloading " + studentRepository.save(new Student("Rock", "Lee")));
        log.info("Preloading " + studentRepository.save(new Student("Shikamaru", "Nara")));
        log.info("Preloading " + studentRepository.save(new Student("Kakashi", "Hatake")));
        log.info("Preloading " + studentRepository.save(new Student("Konohamaru", "Sarutobi")));
        log.info("Preloading " + studentRepository.save(new Student("Neji", "Hyuga")));
        log.info("Preloading " + studentRepository.save(new Student("Minato", "Namikaze")));
        log.info("Preloading " + studentRepository.save(new Student("Sarada", "Uchiha")));
        log.info("Preloading " + studentRepository.save(new Student("Kabuto", "Yakushi")));
        log.info("Preloading " + studentRepository.save(new Student("Guts", "Iwanaga")));
        log.info("Preloading " + studentRepository.save(new Student("Casca", "Yukinari")));
        log.info("Preloading " + studentRepository.save(new Student("Farnese", "Hikasa")));
        log.info("Preloading " + studentRepository.save(new Student("Shierke", "Lee")));
        log.info("Preloading " + teacherRepository.save(t1));
        log.info("Preloading " + teacherRepository.save(t2));
        t1.addStudent(s1);
        t1.addStudent(s2);
        t2.addStudent(s3);
        t2.addStudent(s4);
        teacherRepository.save(t1);
        teacherRepository.save(t2);
      };
    }

}
