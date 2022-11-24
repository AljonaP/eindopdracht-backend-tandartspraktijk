package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.AuthDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.JwtService;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.MyUserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;


    public AuthController(AuthenticationManager man, JwtService service) {
        this.authManager = man;
        this.jwtService = service;
    }

    @PostMapping("/haaientanden/auth")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authDto.username, authDto.password);

        try {
            Authentication auth = authManager.authenticate(usernamePasswordAuthenticationToken);
            MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(myUserDetails);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body("Token generated");
        }
        catch (AuthenticationException authenticationException) {
            return new ResponseEntity(authenticationException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}