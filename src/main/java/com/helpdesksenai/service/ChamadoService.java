package com.helpdesksenai.service;

import com.helpdesksenai.DTO.ChamadoDTO;
import com.helpdesksenai.entity.Chamado;
import com.helpdesksenai.entity.Cliente;
import com.helpdesksenai.entity.Tecnico;
import com.helpdesksenai.enuns.PrioridadeEnum;
import com.helpdesksenai.enuns.StatusEnum;
import com.helpdesksenai.repository.ChamadoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;


    public Chamado findById(Integer id) {
        Chamado chamado = chamadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));
        return chamado;
    }

    public List<Chamado> findAll() {
        List<Chamado> chamdo = chamadoRepository.findAll();
        return chamdo;
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(null);
        return chamadoRepository.save(novoChamado(chamadoDTO));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado atualizaChamado = findById(id);
        atualizaChamado = novoChamado(chamadoDTO);
        return chamadoRepository.save(atualizaChamado);
    }

    public Chamado novoChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnico = tecnicoService.findByID(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findByID(chamadoDTO.getCliente());
        Chamado chamado = new Chamado();
        if (chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }
        if (chamadoDTO.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(PrioridadeEnum.toEnum(chamadoDTO.getPrioridade().getCodigo()));
        chamado.setStatus(StatusEnum.toEnum(chamadoDTO.getStatus().getCodigo()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacao(chamadoDTO.getObservacao());
        return chamado;
    }
}
