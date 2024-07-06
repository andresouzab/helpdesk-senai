package com.helpdesksenai.resource;


import com.helpdesksenai.DTO.ChamadoDTO;
import com.helpdesksenai.entity.Chamado;
import com.helpdesksenai.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamado")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @PostMapping
    public ResponseEntity<ChamadoDTO> criar(@Valid @RequestBody ChamadoDTO chamadoDTO){
        Chamado chamado = service.create(chamadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).body(new ChamadoDTO(chamado));
    };

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscarTodos(){
        List<Chamado> chamadoList = service.findAll();
        return ResponseEntity.ok().body(chamadoList.stream().map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> buscarPorId(@PathVariable Integer id){
        Chamado chamado = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> atualizar(@PathVariable Integer id,@Valid @RequestBody ChamadoDTO chamadoDTO){
        Chamado chamado = service.update(id, chamadoDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

}
