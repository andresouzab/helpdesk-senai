package com.helpdesksenai.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesksenai.entity.Chamado;
import com.helpdesksenai.entity.Cliente;
import com.helpdesksenai.entity.Tecnico;
import com.helpdesksenai.enuns.PerfilEnum;
import com.helpdesksenai.enuns.PrioridadeEnum;
import com.helpdesksenai.enuns.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @NotNull(message = "O campo PRIORIDADE é requerido")
    private PrioridadeEnum prioridade;
    @NotNull(message = "O campo STATUS é requerido")
    private StatusEnum status;
    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;
    @NotNull(message = "O campo OBSERVACAO é requerido")
    private String observacao;
    @NotNull(message = "O campo TECNICO é requerido")
    private Integer tecnico;
    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;


    public ChamadoDTO() {

    }
    public ChamadoDTO(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade();
        this.status = chamado.getStatus();
        this.titulo = chamado.getTitulo();
        this.observacao = chamado.getObservacao();
        this.tecnico = chamado.getTecnico().getId();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public PrioridadeEnum getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeEnum prioridade) {
        this.prioridade = prioridade;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}


