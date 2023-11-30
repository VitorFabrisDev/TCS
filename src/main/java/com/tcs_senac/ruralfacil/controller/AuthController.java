package com.tcs_senac.ruralfacil.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.apache.commons.validator.routines.EmailValidator;
import com.tcs_senac.ruralfacil.config.security.jwt.JwtUtils;
import com.tcs_senac.ruralfacil.config.security.services.UserDetailsImpl;
import com.tcs_senac.ruralfacil.exception.TokenRefreshException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Enum.Roles;
import com.tcs_senac.ruralfacil.model.RefreshToken;
import com.tcs_senac.ruralfacil.payload.request.LoginRequest;
import com.tcs_senac.ruralfacil.payload.request.SignupRequest;
import com.tcs_senac.ruralfacil.payload.request.TokenRefreshRequest;
import com.tcs_senac.ruralfacil.payload.response.JwtResponse;
import com.tcs_senac.ruralfacil.payload.response.MessageResponse;
import com.tcs_senac.ruralfacil.payload.response.TokenRefreshResponse;
import com.tcs_senac.ruralfacil.repository.AcessoPessoaRepository;
import com.tcs_senac.ruralfacil.repository.AgricultorRepository;
import com.tcs_senac.ruralfacil.repository.ClienteRepository;
import com.tcs_senac.ruralfacil.repository.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AcessoPessoaRepository acessoPessoaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    AgricultorRepository agricultorRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        Optional<AcessoPessoa> acessoPessoaExistente = acessoPessoaRepository.findByLogin(loginRequest.getUsername());
        if (acessoPessoaExistente.isPresent()) {
            AcessoPessoa acessoPessoa = acessoPessoaExistente.get();

            Long qntAcessoAtual = acessoPessoa.getQtdAcesso();

            acessoPessoa.setQtdAcesso(qntAcessoAtual + 1);
            acessoPessoa.setDtUltAcesso(LocalDateTime.now());

            acessoPessoaRepository.save(acessoPessoa);
        } else {
            return ResponseEntity.status(400).body("Usuário não encontrado");
        }



        return ResponseEntity.ok(new JwtResponse(
                jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                roles
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (acessoPessoaRepository.existsByLogin(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Usuário já existente!"));
        }

        if (!EmailValidator.getInstance().isValid(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid email address!"));
        }

        Roles roles = Roles.ROLE_CLIENTE;

        String selectedRole = signUpRequest.getRole().toString();


        switch (selectedRole) {
            case "[ROLE_AGRICULTOR]":
                roles = Roles.ROLE_AGRICULTOR;
                break;
            case "[ROLE_CLIENTE]":
                roles = Roles.ROLE_CLIENTE;
                break;
            case "[ROLE_ADMIN]":
                roles = Roles.ROLE_ADMIN;
                break;
            default:
                roles = Roles.ROLE_CLIENTE;
        }

        AcessoPessoa acessoPessoa = new AcessoPessoa(
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                roles
        );

        acessoPessoa.setAdmin(roles);
        acessoPessoaRepository.save(acessoPessoa);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getAcessoPessoa)
                .map(acessoPessoa -> {
                    String token = jwtUtils.generateTokenFromUsername(acessoPessoa.getLogin());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
