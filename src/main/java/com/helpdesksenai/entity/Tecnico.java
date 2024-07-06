package com.helpdesksenai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesksenai.DTO.TecnicoDTO;
import com.helpdesksenai.enuns.PerfilEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "tecnico", fetch = FetchType.EAGER)
    List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(PerfilEnum.CLIENTE);

    }

    public Tecnico(TecnicoDTO dto) {
        super(dto.getId(), dto.getNome(), dto.getCpf(), dto.getEmail(), dto.getSenha(), dto.getDataCriacao());
        addPerfil(PerfilEnum.CLIENTE);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

}
