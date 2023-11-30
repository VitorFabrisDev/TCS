package com.tcs_senac.ruralfacil.dto;

import com.tcs_senac.ruralfacil.model.AcessoPessoa;
import com.tcs_senac.ruralfacil.model.Enum.Roles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AcessoPessoaDto {

    private String login;
    private String password;
    private long qtdAcesso;
    private LocalDateTime dtUltAcesso;
    private Roles admin;

    public AcessoPessoaDto() {
    }

    public static AcessoPessoaDto fromEntity(AcessoPessoa acessoPessoa) {
        AcessoPessoaDto dto = new AcessoPessoaDto();
        dto.setLogin(acessoPessoa.getLogin());
        dto.setPassword(acessoPessoa.getPassword());
        dto.setQtdAcesso(acessoPessoa.getQtdAcesso());
        dto.setDtUltAcesso(acessoPessoa.getDtUltAcesso());
        dto.setAdmin(acessoPessoa.getAdmin());
        return dto;
    }

    public AcessoPessoa toEntity() {
        AcessoPessoa acessoPessoa = new AcessoPessoa();

        acessoPessoa.setLogin(this.getLogin());
        acessoPessoa.setPassword(this.getPassword());
        acessoPessoa.setQtdAcesso(this.getQtdAcesso());
        acessoPessoa.setDtUltAcesso(this.getDtUltAcesso());
        acessoPessoa.setAdmin(this.getAdmin());
        return acessoPessoa;
    }

    public static List<AcessoPessoaDto> fromEntity(List<AcessoPessoa> acessosPessoa) {
        return acessosPessoa.stream().map(AcessoPessoaDto::fromEntity).collect(Collectors.toList());
    }

    public static Page<AcessoPessoaDto> fromEntity(Page<AcessoPessoa> acessosPessoa, Pageable pageable) {
        List<AcessoPessoaDto> acessosPessoaDTO = acessosPessoa.stream().map(AcessoPessoaDto::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(acessosPessoaDTO, pageable, acessosPessoa.getTotalElements());
    }

    // Adicione getters e setters para os campos da classe AcessoPessoaDTO

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getQtdAcesso() {
        return qtdAcesso;
    }

    public void setQtdAcesso(long qtdAcesso) {
        this.qtdAcesso = qtdAcesso;
    }

    public LocalDateTime getDtUltAcesso() {
        return dtUltAcesso;
    }

    public void setDtUltAcesso(LocalDateTime dtUltAcesso) {
        this.dtUltAcesso = dtUltAcesso;
    }

    public Roles getAdmin() {
        return admin;
    }

    public void setAdmin(Roles admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcessoPessoaDto that = (AcessoPessoaDto) o;
        return  Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                qtdAcesso == that.qtdAcesso &&
                Objects.equals(dtUltAcesso, that.dtUltAcesso) &&
                admin == that.admin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, qtdAcesso, dtUltAcesso, admin);
    }
}
