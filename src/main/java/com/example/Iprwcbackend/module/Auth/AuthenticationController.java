package com.example.Iprwcbackend.module.Auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Value("${recaptcha.secretKey}")
    private String recaptchaSecretKey;
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/verify-recaptcha")
    public ResponseEntity<?> verifyRecaptcha(@RequestParam("response") String captchaResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.google.com/recaptcha/api/siteverify")
                .queryParam("secret", recaptchaSecretKey)
                .queryParam("response", captchaResponse);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                new HttpEntity<>(headers),
                String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response.getBody(), HttpStatus.BAD_REQUEST);
        }
    }
}
