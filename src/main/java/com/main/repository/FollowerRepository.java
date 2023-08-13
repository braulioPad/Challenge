package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.entity.Follower;
import com.main.entity.Person;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

	@Query("SELECT f.followerPerson FROM Follower f WHERE f.person.id = :personId")
	List<Person> findByFollowers(@Param("personId") Long personId);
	
	
	 @Query("SELECT f FROM Follower f WHERE f.person.id = :personId AND f.followerPerson.id = :followerId")
		Follower findFollowerByPersonIdAndFollowerId(
	        @Param("personId") Long personId,
	        @Param("followerId") Long followerId
	    );
	 
}
