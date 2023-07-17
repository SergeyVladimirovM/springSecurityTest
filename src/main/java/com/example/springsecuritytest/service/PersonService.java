package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    List<Person> findAllPerson();
    UUID savePerson(Person person);
}
