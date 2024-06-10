package com.mugishap.rca.springboot.v1.services;

import com.mugishap.rca.springboot.v1.enums.ERole;
import com.mugishap.rca.springboot.v1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IUserService {

    Page<User> getAll(Pageable pageable);

    User getById(UUID id);

    User create(User user);
    boolean delete(UUID id);

    Page<User> getAllByRole(Pageable pageable, ERole role);

    User getLoggedInUser();

    User getByEmail(String email);

}