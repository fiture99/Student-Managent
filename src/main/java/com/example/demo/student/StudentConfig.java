package com.example.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    "Mariam Njie",
                    "Mariam@gamil.com",
                    LocalDate.of(2000, Month.DECEMBER, 5),
                    "Computer Science",
                    6,
                    "Disabled"


            );

            Student lamin = new Student(
                    "Lamin Jawneh",
                    "ljawneh@gamil.com",
                    LocalDate.of(1999, Month.MARCH, 5),
                    "Maths",
                    77,
                    "Active"


            );

            Student haja = new Student(
                    "Haja Mbyr",
                    "hh@gamil.com",
                    LocalDate.of(1996, Month.MARCH, 5),
                    "Maths",
                    65,
                    "Graduated"


            );

            repository.saveAll(
                    List.of(mariam, lamin, haja)
            );




        };
    }


}
