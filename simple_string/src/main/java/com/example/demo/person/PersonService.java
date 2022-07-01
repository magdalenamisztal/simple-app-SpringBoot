package com.example.demo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        Optional<Person> personByEmail = personRepository.findPersonByEmail(person.getEmail());
        if(personByEmail.isPresent()){
            throw new IllegalStateException("Email taken!");
        }
        System.out.println(person);
        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if(!exists) {
            throw new IllegalStateException("Person with id "+personId+" doesn't exist!");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId, String name, String email) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "Person with id "+personId+" doesn't exist"
                ));
        if(name !=null && name.length()>0
                && !Objects.equals(person.getName(), name)){
            person.setName(name);
        }

        if(email != null && email.length()>0 &&
                !Objects.equals(person.getEmail(), email)){
            Optional<Person> personByEmail = personRepository.findPersonByEmail(email);
            if(personByEmail.isPresent()) {
                throw new IllegalStateException("Email taken!");
            }
            person.setEmail(email);
        }
    }
}
