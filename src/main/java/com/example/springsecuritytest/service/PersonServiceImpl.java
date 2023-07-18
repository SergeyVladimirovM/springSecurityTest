package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.Person;
import com.example.springsecuritytest.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonById(UUID id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @Override
    public UUID savePerson(Person person) {
        return personRepository.save(person).getId();
    }
}
