package com.bingo.api.bingo_manager.controller;

import com.bingo.api.bingo_manager.domain.Role;
import com.bingo.api.bingo_manager.dto.UsuarioDTO;
import com.bingo.api.bingo_manager.dto.input.LoginInput;
import com.bingo.api.bingo_manager.dto.input.LoginResponse;
import com.bingo.api.bingo_manager.dto.input.UsuarioInput;
import com.bingo.api.bingo_manager.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtEncoder jwtEncoder;

    private final UsuarioService usuarioService;

    private final BCryptPasswordEncoder passwordEncoder;


    public AuthController(JwtEncoder jwtEncoder, UsuarioService usuarioService, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginInput loginInput) {
        var usuario = usuarioService.findByEmail(loginInput.email());
        if(usuario.isEmpty() || !usuario.get().isLoginCorrect(loginInput, passwordEncoder)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha incorretos");
        }

        var expiresIn = 300L;
        var now = Instant.now();

        var scope = usuario.get().getRoles()
                .stream()
                .map(Role::getNome)
                .collect(Collectors.joining(" "));
        var claims = JwtClaimsSet.builder()
                .issuer("bingo-manager")
                .subject(usuario.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioInput usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.create(usuario));
    }
}
