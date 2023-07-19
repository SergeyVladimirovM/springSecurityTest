package com.example.springsecuritytest.repository;

import com.example.springsecuritytest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Person findPersonByNickName(String nickname);
}
