package com.example.airlines.controller;

import com.example.airlines.dto.LoginDto;
import com.example.airlines.dto.RegistrationDto;
import com.example.airlines.exception.InvalidLoginDataException;
import com.example.airlines.exception.InvalidRegistrationDataException;
import com.example.airlines.exception.UserAlreadyLoggedInException;
import com.example.airlines.model.User;
import com.example.airlines.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final UserService userService;
    private User user;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationDto registrationDto) {
        if(registrationDto == null || registrationDto.getFirstName() == null || registrationDto.getLastName() == null
        || registrationDto.getEmail() == null || registrationDto.getPassword() == null || registrationDto.getAddress() == null
        || registrationDto.getCity() == null || registrationDto.getCountry() == null || registrationDto.getFirstName().trim().equals("") || registrationDto.getLastName().trim().equals("")
                || registrationDto.getEmail().trim().equals("") || registrationDto.getPassword().trim().equals("")|| registrationDto.getAddress().trim().equals("")
                || registrationDto.getCity().trim().equals("") || registrationDto.getCountry().trim().equals("")){
            throw new InvalidRegistrationDataException("Registration Data incomplete");
        }
        try {
            User registeredUser = userService.registerUser(registrationDto);
            return ResponseEntity.ok().body(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        if (loginDto == null || loginDto.getEmail() == null || loginDto.getEmail().trim().equals("") || loginDto.getPassword() == null || loginDto.getPassword().trim().equals("")) {
            throw new InvalidLoginDataException("Login Data incomplete");
        } User user = userService.authenticateUSer(loginDto);
        if (user == null) {
            throw new InvalidLoginDataException("Email or password incorrect!");
        }

        try {
            request.login(loginDto.getEmail(), loginDto.getPassword());

        } catch (Exception e){
            e.printStackTrace();
            throw new UserAlreadyLoggedInException( "Already logged!");
        }
        return new ResponseEntity<String>("User logged in successfully", HttpStatus.OK);

    }
}
