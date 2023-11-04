package com.tcs_senac.ruralfacil.controller;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;


import com.tcs_senac.ruralfacil.config.security.jwt.JwtUtils;
import com.tcs_senac.ruralfacil.config.security.services.UserDetailsImpl;
import com.tcs_senac.ruralfacil.exception.NotFoundException;
import com.tcs_senac.ruralfacil.exception.TokenRefreshException;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Agricultor;
import com.tcs_senac.ruralfacil.model.Cliente;
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

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                roles
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (acessoPessoaRepository.existsByLogin(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Roles roles = null;

        Set<String> rolesSet = signUpRequest.getRole();
        String selectedRole = null;

        if (rolesSet != null && !rolesSet.isEmpty()) {

            selectedRole = rolesSet.iterator().next();
        }

        AcessoPessoa acessoPessoa = new AcessoPessoa(
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                roles
        );

        acessoPessoa.setAdmin(roles);
        acessoPessoaRepository.save(acessoPessoa);

        if (Roles.ROLE_AGRICULTOR.name().equals(selectedRole)) {
            roles = Roles.ROLE_AGRICULTOR;
            Agricultor agricultor = signUpRequest.getAgricultor();
            if (agricultor != null) {
                Agricultor newAgricultor = new Agricultor();
                newAgricultor.setCpf(agricultor.getCpf());
                newAgricultor.setNome(agricultor.getNome());
                newAgricultor.setDataNascimento(agricultor.getDataNascimento());
                newAgricultor.setEmail(agricultor.getEmail());
                newAgricultor.setWhatsApp(agricultor.getWhatsApp());
                newAgricultor.setCaf(agricultor.getCaf());
                newAgricultor.setOrganico(agricultor.getOrganico());
                newAgricultor.setAtivo(agricultor.getAtivo());
                newAgricultor.setInscricaoEstadual(agricultor.getInscricaoEstadual());
                newAgricultor.setAcessoPessoa(acessoPessoa);

                agricultorRepository.save(newAgricultor);
            }
            Cliente cliente = signUpRequest.getCliente();
            if (cliente != null) {
                Cliente newCliente = new Cliente();
                newCliente.setCpf(cliente.getCpf());
                newCliente.setNome(cliente.getNome());
                newCliente.setDataNascimento(cliente.getDataNascimento());
                newCliente.setEmail(cliente.getEmail());
                newCliente.setWhatsApp(cliente.getWhatsApp());
                newCliente.setAcessoPessoa(acessoPessoa);

                clienteRepository.save(newCliente);

            }

        }  else if (Roles.ROLE_ADMIN.name().equals(selectedRole)){
            roles = Roles.ROLE_ADMIN;
        } else if (Roles.ROLE_CLIENTE.name().equals(selectedRole)) {
            roles = Roles.ROLE_CLIENTE;
            Cliente cliente = signUpRequest.getCliente();
            if (cliente != null) {

                Cliente newCliente = new Cliente();
                newCliente.setCpf(cliente.getCpf());
                newCliente.setNome(cliente.getNome());
                newCliente.setDataNascimento(cliente.getDataNascimento());
                newCliente.setEmail(cliente.getEmail());
                newCliente.setWhatsApp(cliente.getWhatsApp());
                newCliente.setAcessoPessoa(acessoPessoa);

                clienteRepository.save(newCliente);
            }
            else {
                throw new NotFoundException("Perfil não encontrado: " + selectedRole);
            }

        }


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
