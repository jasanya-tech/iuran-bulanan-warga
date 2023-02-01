package com.iuran_bulanan_warga.Services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.iuran_bulanan_warga.Config.JwtService;
import com.iuran_bulanan_warga.Helpers.DTO.Requests.LoginRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Requests.UserRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.LoginResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Helpers.Entities.ERole;
import com.iuran_bulanan_warga.Models.Entities.Role;
import com.iuran_bulanan_warga.Models.Entities.Users;
import com.iuran_bulanan_warga.Models.Repositories.RoleRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity<?> serviceRegister(UserRequest userRequest) {
    try {
      Boolean checkEmail = userRepository.existByEmail(userRequest.getEmail());

      if (checkEmail) {
        return ResponseEntity.badRequest().body(new MessageResponse("Error:Email is Already taken"));
      }

      Users user = new Users(
        userRequest.getFullName(),
        userRequest.getEmail(),
        passwordEncoder.encode(userRequest.getPassword()),
        userRequest.getPhoneNumber()
      );

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
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceLogin(LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          loginRequest.getEmail(),
          loginRequest.getPassword()
        )
      );

      var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
      var jwtToken = jwtService.generateToken(user);

      return ResponseEntity.ok().body(
        LoginResponse.builder().token(jwtToken).build()
      );
        
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }
}
