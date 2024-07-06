package com.helpdesksenai.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesksenai.entity.Cliente;
import com.helpdesksenai.enuns.PerfilEnum;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "O campo noe é requerido")
    protected String nome;
    @NotNull(message = "O campo CPF é requerido")
    @CPF
    protected String cpf;
    @NotNull(message = "o campo EMAIL é requerido")
    protected String email;
    @NotNull(message = "o campo SENHA é requerido")
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();


    public ClienteDTO(){
        addPerfil(PerfilEnum.CLIENTE);
    }
    public ClienteDTO(Cliente obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.dataCriacao = dataCriacao;
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        addPerfil(PerfilEnum.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<PerfilEnum> getPerfis() {
        return perfis.stream().map(x -> PerfilEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(PerfilEnum perfil) {
    this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}


