package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Define any custom methods related to Message entity here (if needed)
    // ...
}
