package com.mugishap.rca.springboot.v1.controllers;

import com.mugishap.rca.springboot.v1.enums.ERole;
import com.mugishap.rca.springboot.v1.exceptions.BadRequestException;
import com.mugishap.rca.springboot.v1.models.Role;
import com.mugishap.rca.springboot.v1.models.User;
import com.mugishap.rca.springboot.v1.payload.request.CreateUserDTO;
import com.mugishap.rca.springboot.v1.payload.response.ApiResponse;
import com.mugishap.rca.springboot.v1.repositories.IRoleRepository;
import com.mugishap.rca.springboot.v1.services.IUserService;
import com.mugishap.rca.springboot.v1.utils.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final IRoleRepository roleRepository;

    @GetMapping(path = "/current-user")
    public ResponseEntity<ApiResponse> currentlyLoggedInUser() {
        return ResponseEntity.ok(ApiResponse.success("Currently logged in user fetched", userService.getLoggedInUser()));
    }

    @GetMapping(path = "/all")
    public Page<User> getAllUsers(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return userService.getAll(pageable);
    }

    @GetMapping(path = "/all/{role}")
    public Page<User> getAllUsersByRole(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit,
            @PathVariable(value = "role") ERole role
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return userService.getAllByRole(pageable, role);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid CreateUserDTO dto) {

        User user = new User();

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        Role role = roleRepository.findByName(dto.getRole()).orElseThrow(
                () -> new BadRequestException("User Role not set"));

        user.setEmail(dto.getEmail());
        user.setNames(dto.getNames());
        user.setTelephone(dto.getTelephone());
        user.setPassword(encodedPassword);
        user.setRoles(Collections.singleton(role));

        User entity = this.userService.create(user);

        return ResponseEntity.ok(ApiResponse.success("User created successfully", entity));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteMyAccount() {
        User user = this.userService.getLoggedInUser();
        this.userService.delete(user.getId());
        return ResponseEntity.ok(ApiResponse.success("Account deleted successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteByAdmin(
            @PathVariable(value = "id") UUID id
    ) {
        this.userService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Account deleted successfully"));
    }

}