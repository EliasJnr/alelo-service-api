package com.eliasjr.aleloserviceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eliasjr.aleloserviceapi.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
