package ru.liga.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.crud.entity.User;
import ru.liga.crud.response.JwtResponse;
import ru.liga.crud.jwt.JwtUtils;
import ru.liga.crud.service.ResourceBundleService;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private static final ResourceBundleService resourceBundleService = new ResourceBundleService();
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);

            JwtResponse jwtResponse = JwtResponse.builder()
                    .authType(jwtUtils.getAuthType())
                    .jwtToken(jwtToken)
                    .build();

            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (AuthenticationException e) {
            JwtResponse jwtResponse = JwtResponse.builder()
                    .message(resourceBundleService.getMessage("invalidLoginOrPassword"))
                    .build();
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        }
    }
}
