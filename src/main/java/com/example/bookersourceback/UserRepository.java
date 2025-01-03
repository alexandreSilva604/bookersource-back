package com.example.bookersourceback;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value="SELECT count(*) FROM User u WHERE u.email=:email and u.password=:password")
    long countByEmailAndPassword(String email, String password);
}
