package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Message;
import com.main.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // Define a custom method to search all messages from a specific person
   List<Message> findAllMessagesByPerson(Person person);
}