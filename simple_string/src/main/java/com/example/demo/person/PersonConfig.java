package com.example.demo.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository){
        return args -> {
            Person magda = new Person(
                    "Magdalena",
                    "magdalena@gmail.com",
                    LocalDate.of(1996, Month.SEPTEMBER,10)
            );
            Person janek = new Person(
                    "Jan",
                    "janek@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 12)
            );

            repository.saveAll(List.of(magda,janek));
        };
    }
}
