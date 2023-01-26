package com.iuran_bulanan_warga.Services;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.UserRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Helpers.Entities.ERole;
import com.iuran_bulanan_warga.Models.Entities.Role;
import com.iuran_bulanan_warga.Models.Entities.Users;
import com.iuran_bulanan_warga.Models.Repositories.RoleRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Method for getting All users
    public ResponseEntity<?> serviceGetAll() {
        try {
            List<Users> users = userRepository.findAll();

            if (users.isEmpty()) {
                throw new NoSuchElementException("No users found");
            }
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    // Method for getting user by id
    public ResponseEntity<?> serviceGetById(Integer id) {
        try {
            Optional<Users> user = userRepository.findById(id);
            if (user.isPresent()) {
                return ResponseEntity.ok().body(user.get());
            } else {
                throw new NoSuchElementException("No users found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

    }

    // Method for creating user
    public ResponseEntity<?> serviceCreate(UserRequest userRequest) {
        try {
            Boolean checkEmail = userRepository.existByEmail(userRequest.getEmail());
            if (checkEmail) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error:Email is Already taken"));
            }
            Users user = new Users(
                    userRequest.getFullName(),
                    userRequest.getEmail(),
                    passwordEncoder.encode(userRequest.getPassword()),
                    userRequest.getPhoneNumber());
            Set<String> reqRole = userRequest.getRole();
            Set<Role> roles = new HashSet<>();
            if (reqRole == null) {
                Optional<Role> userRole = roleRepository.findByRoleName(ERole.WARGA);
                if (userRole.isPresent()) {
                    roles.add(userRole.get());
                } else {
                    Role role = new Role();
                    role.setRoleName(ERole.WARGA);
                    roleRepository.save(role);
                    roles.add(role);
                }
            } else {
                reqRole.forEach(roleName -> {
                    String role = roleName.toUpperCase();
                    Optional<Role> userRole = roleRepository.findByRoleName(ERole.valueOf(role));
                    if (userRole.isPresent()) {
                        roles.add(userRole.get());
                    } else {
                        throw new IllegalStateException("Error : Role " + role + " Not found");
                    }
                });
            }
            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    // Method for updating user
    public ResponseEntity<?> serviceUpdate(Integer id, UserRequest userRequest) {
        try {
            Optional<Users> users = userRepository.findById(id);
            if (!users.isPresent()) {
                throw new NoSuchElementException("User " + id + " does not exist");
            }
            Users user = users.get();
            if (user.getEmail() != userRequest.getEmail()) {
                Boolean checkEmail = userRepository.existByEmail(userRequest.getEmail());
                if (checkEmail) {
                    throw new IllegalArgumentException("Error:Email is Already taken");
                }
            }
            user.setFullName(userRequest.getFullName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            Set<String> reqRole = userRequest.getRole();
            Set<Role> roles = new HashSet<>();
            if (reqRole == null) {
                Optional<Role> userRole = roleRepository.findByRoleName(ERole.WARGA);
                if (userRole.isPresent()) {
                    roles.add(userRole.get());
                } else {
                    Role role = new Role();
                    role.setRoleName(ERole.WARGA);
                    roleRepository.save(role);
                    roles.add(role);
                }
            } else {
                reqRole.forEach(roleName -> {
                    String role = roleName.toUpperCase();
                    Optional<Role> userRole = roleRepository.findByRoleName(ERole.valueOf(role));
                    if (userRole.isPresent()) {
                        roles.add(userRole.get());
                    } else {
                        throw new IllegalStateException("Error : Role " + role + " Not found");
                    }
                });
            }
            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    // Method for Delete By Id User
    public ResponseEntity<?> serviceDeleteById(Integer id) {
        try {
            Optional<Users> users = userRepository.findById(id);
            if (!users.isPresent()) {
                throw new NoSuchElementException("User " + id + " does not exist");
            }
            userRepository.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("User ID : " + id + " has been deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    // Method for deleted all users
    public ResponseEntity<?> serviceDeleteAll() {
        try {
            userRepository.deleteAll();
            return ResponseEntity.ok().body(new MessageResponse("All users have been deleted"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
