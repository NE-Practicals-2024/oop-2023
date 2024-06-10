package com.mugishap.rca.springboot.v1.repositories;

import com.mugishap.rca.springboot.v1.enums.ERole;
import com.mugishap.rca.springboot.v1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID userID);

    Optional<User> findByEmail(String email);

    Page<User> findByRoles(Pageable pageable, ERole role);
}
