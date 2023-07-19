package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface PersonService extends UserDetailsService  {
    List<Person> findAllPerson();
    Person findPersonById(UUID id);
    UUID savePerson(Person person);
    Person findPersonByNickname(String nickname);
    UserDetails loadUserByUsername(String nickname);
}
