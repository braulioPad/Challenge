package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.entity.Message;
import com.main.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("SELECT m FROM Message m WHERE m.person.id = :personId AND (:searchWord IS NULL OR m.content LIKE %:searchWord%)")
	List<Message> findAllMessagesByPersonIdAndSearchWord(@Param("personId") Long personId,
			@Param("searchWord") String searchWord);

	@Query("SELECT m FROM Message m WHERE m.person.id = :personId")
	List<Message> findAllMessagesByPersonId(@Param("personId") Long personId);
}