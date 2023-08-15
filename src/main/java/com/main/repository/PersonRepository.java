package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Person;
import com.main.projection.PopularFollowerProjection;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByName(String name);

	@Query(nativeQuery = true, value = "SELECT p.id AS personId, p.name AS personName, COUNT(f.id) AS followerCount "
			+ "FROM people p " + "LEFT JOIN followers f ON p.id = f.person_id " + "GROUP BY p.id, p.name "
			+ "ORDER BY followerCount DESC")
	List<PopularFollowerProjection> findAllUsersWithMostPopularFollower();
}