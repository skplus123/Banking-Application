package com.Banking.Application.jwt;

import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling authentication-related operations.
 * <p>
 * This controller provides an endpoint for generating JWT tokens for a given username.
 * The generated token can then be used to authenticate subsequent requests.
 * </p>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Generates a JWT token for the specified username.
     * <p>
     * This endpoint accepts a username as a request parameter and returns a JWT token
     * that can be used for authentication in further requests.
     * </p>
     *
     * @param username the username for which the token is generated
     * @return a JWT token as a {@link String}
     */
    @PostMapping("/login")
    public String login(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }
}

