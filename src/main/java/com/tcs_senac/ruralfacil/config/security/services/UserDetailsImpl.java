package com.tcs_senac.ruralfacil.config.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Enum.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long idCliente;
    private Long idAgricultor;
    private String login;
    private Roles admin;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, long idCliente, long idAgricultor, String login, String password,
                           Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.idCliente = idCliente;
        this.idAgricultor = idAgricultor;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Optional<AcessoPessoa> acessoPessoa) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(acessoPessoa.get().getAdmin().getRoles()));

        return new UserDetailsImpl(
                acessoPessoa.get().getId(),
                acessoPessoa.get().getCliente().getId(),
                acessoPessoa.get().getAgricultor().getId(),
                acessoPessoa.get().getLogin(),
                acessoPessoa.get().getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}