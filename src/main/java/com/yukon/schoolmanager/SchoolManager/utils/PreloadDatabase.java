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
        log.info("Preloading " + studentRepository.save(new Student("Naruto", "Uzumaki")));
        log.info("Preloading " + studentRepository.save(new Student("Sasuke", "Uchiha")));
        log.info("Preloading " + studentRepository.save(new Student("Sakura", "Haruno")));
        log.info("Preloading " + studentRepository.save(new Student("Hinata", "Hyuga")));
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
        log.info("Preloading " + teacherRepository.save(new Teacher("Madara Uchiha")));
        log.info("Preloading " + teacherRepository.save(new Teacher("Griffith")));
      };
    }

}
