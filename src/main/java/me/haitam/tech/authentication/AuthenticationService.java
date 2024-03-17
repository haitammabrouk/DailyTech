package me.haitam.tech.authentication;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.haitam.tech.jwt.JwtService;
import me.haitam.tech.mail.EmailService;
import me.haitam.tech.user.Role;
import me.haitam.tech.user.User;
import me.haitam.tech.user.UserRepository;

@Service
public class AuthenticationService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final EmailService emailService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager,
                                 EmailService emailService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }

    public AuthenticationResponse register(User request){
        boolean isEmailAlreadyExist = repository.findByEmail(request.getEmail()).isPresent();
        boolean isUsernameAlreadyExist = repository.findByUsername(request.getUsername()).isPresent();
    
        if(isEmailAlreadyExist || isUsernameAlreadyExist){
            return new AuthenticationResponse(isEmailAlreadyExist, isUsernameAlreadyExist);
        } else {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setProfil(request.getProfil());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);
            
            user = repository.save(user);

            String token = jwtService.generateToken(user);

            SimpleMailMessage mail = emailService.setEmail(request.getEmail(), request.getFirstname().substring(0, 1).toUpperCase() + request.getFirstname().substring(1));
            emailService.sendEmail(mail);

            return new AuthenticationResponse(token);
        }
    }
    
    

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

}
