package com.exemplo.tenissonjr.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.exemplo.tenissonjr.model.User;

public interface UserRepository extends CrudRepository<User, String> {
   Optional<User> findByUserName(String userName);
}