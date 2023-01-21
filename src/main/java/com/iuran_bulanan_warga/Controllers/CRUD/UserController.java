package com.iuran_bulanan_warga.Controllers.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.UserRequest;
import com.iuran_bulanan_warga.Services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Users", description = "CRUD")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get All User", description = "Endpoint for getting all users")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return userService.serviceGetAll();
    }

    @Operation(summary = "Get One User", description = "Endpoint for getting one users")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return userService.serviceGetById(id);
    }

    @Operation(summary = "Create User", description = "Endpoint for Createing user")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        return userService.serviceCreate(userRequest);
    }

    @Operation(summary = "Update User", description = "Endpoint for Updateing user")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserRequest userRequest,
            @PathVariable(value = "id", required = true) Integer id) {
        System.out.println(id);
        return userService.serviceUpdate(id, userRequest);
    }

    @Operation(summary = "Delete User", description = "Endpoint for deleteing users")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Integer id) {
        return userService.serviceDeleteById(id);
    }

    @Operation(summary = "Delete All User", description = "Endpoint for deleteing all users")
    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        return userService.serviceDeleteAll();
    }
}
