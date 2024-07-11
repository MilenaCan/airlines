package com.example.airlines.service;

import com.example.airlines.dao.RoleDao;
import com.example.airlines.dao.UserDao;
import com.example.airlines.dto.LoginDto;
import com.example.airlines.dto.RegistrationDto;
import com.example.airlines.model.Role;
import com.example.airlines.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender mailSender;


    public boolean isEmailAlreadyInUse(String email) {
        return userDao.existsByEmail(email);
    }

    @Autowired
    public UserService(UserDao userRepository, RoleDao roleRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.userDao = userRepository;
        this.roleDao = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }


    public User registerUser(RegistrationDto registrationDTO) {
        if (isEmailAlreadyInUse(registrationDTO.getEmail())) {
            throw new RuntimeException("Email already exist");
        }

        User user = new User();
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setAddress(registrationDTO.getAddress());
        user.setCountry(registrationDTO.getCountry());
        user.setCity(registrationDTO.getCity());
        Role userRole = roleDao.findByRoleName("USER");
        user.setRole(new HashSet<Role>(Arrays.asList(userRole)));


        User savedUser = userDao.save(user);
        sendRegistrationEmail(savedUser);

        return savedUser;
    }


    private void sendRegistrationEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Registration Confirmation");
        mailMessage.setText("You have been registered with the username: " + user.getEmail());

        mailSender.send(mailMessage);
    }

    public User authenticateUSer(LoginDto loginDto) {
        User user = userDao.findByEmail(loginDto.getEmail());
        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<SimpleGrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

    }

}
