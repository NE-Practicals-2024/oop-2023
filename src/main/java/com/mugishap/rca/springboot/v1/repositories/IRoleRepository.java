package com.mugishap.rca.springboot.v1.repositories;

import com.mugishap.rca.springboot.v1.models.Role;
import com.mugishap.rca.springboot.v1.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole role);
}
