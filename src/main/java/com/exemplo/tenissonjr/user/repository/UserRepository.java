package com.exemplo.tenissonjr.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exemplo.tenissonjr.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
   Optional<User> findByUsername(String userName);
}