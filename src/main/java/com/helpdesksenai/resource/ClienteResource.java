package com.helpdesksenai.resource;

import com.helpdesksenai.DTO.ClienteDTO;
import com.helpdesksenai.entity.Cliente;
import com.helpdesksenai.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    //POST
    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = service.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
    }
    //GET
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos(){
        List<Cliente> clienteList = service.findAll();
        return ResponseEntity.ok().body(clienteList.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList()));
    }

    //GET POR ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id){
        Cliente cliente = service.findByID(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = service.update(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }


    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> excluir(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
