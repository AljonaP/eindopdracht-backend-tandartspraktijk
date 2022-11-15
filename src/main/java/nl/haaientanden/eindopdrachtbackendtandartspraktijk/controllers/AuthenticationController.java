//package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;
//
//import nl.haaientanden.eindopdrachtbackendtandartspraktijk.payload.AuthenticationRequest;
//import nl.haaientanden.eindopdrachtbackendtandartspraktijk.payload.AuthenticationResponse;
//import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.CustomUserDetailsService;
//import nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.JwtUtil;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//
//@CrossOrigin
//@RestController
//public class AuthenticationController {
//
//    private AuthenticationManager authenticationManager;
//    private CustomUserDetailsService userDetailsService;
//    private JwtUtil jwtUtil;
//
//    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = customUserDetailsService;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @GetMapping(value = "/authenticated")
//    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
//        return ResponseEntity.ok().body(principal);
//    }
//
//    @PostMapping(value = "/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//
//        String username = authenticationRequest.getUsername();
//        String password = authenticationRequest.getPassword();
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//        }
//        catch (BadCredentialsException ex) {
//            throw new Exception("Incorrect username or password", ex);
//        }
//
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }
//
//}
