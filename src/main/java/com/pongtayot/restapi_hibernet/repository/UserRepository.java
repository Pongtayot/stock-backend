package com.pongtayot.restapi_hibernet.repository;

import com.pongtayot.restapi_hibernet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // SELECT * FROM user WHERE username = 'foo'
    User findByUsername(String username);
}
