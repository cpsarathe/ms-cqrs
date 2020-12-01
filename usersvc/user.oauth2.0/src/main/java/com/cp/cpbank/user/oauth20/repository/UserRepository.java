package com.cp.cpbank.user.oauth20.repository;

import com.cpbank.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'account.userName': ?0}")
    Optional<User> findByUsername(String username);

}
