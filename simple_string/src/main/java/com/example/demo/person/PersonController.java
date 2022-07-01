package com.example.demo.person;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping()
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @PostMapping(path = "/person")
    public void registerNewPerson(@RequestBody Person person) {
        personService.addNewPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(@PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
    }

    @PutMapping(path = "{personId}")
    public void updatePerson(
            @PathVariable("personId") Long personId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        personService.updatePerson(personId, name, email);
    }

}
