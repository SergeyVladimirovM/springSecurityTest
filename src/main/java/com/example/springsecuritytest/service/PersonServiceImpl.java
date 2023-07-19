package com.example.springsecuritytest.service;

import com.example.springsecuritytest.entity.Person;
import com.example.springsecuritytest.entity.Role;
import com.example.springsecuritytest.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{

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
    public Person findPersonByNickname(String nickname) {
        return personRepository.findPersonByNickName(nickname);
    }

    @Override
    public UUID savePerson(Person person) {
        return personRepository.save(person).getId();
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Person person = findPersonByNickname(nickname);
        if(person == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", nickname));
            
        }
        return new User(
                person.getNickName(),
                person.getPassword(),
                person.getRoleList().stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList())
        );
    }
}
