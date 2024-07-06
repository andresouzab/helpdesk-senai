package com.helpdesksenai.service;

import com.helpdesksenai.DTO.ClienteDTO;
import com.helpdesksenai.DTO.TecnicoDTO;
import com.helpdesksenai.entity.Cliente;
import com.helpdesksenai.entity.Pessoa;
import com.helpdesksenai.entity.Tecnico;
import com.helpdesksenai.exceptions.ObjectNotFoundException;
import com.helpdesksenai.repository.ClienteRepository;
import com.helpdesksenai.repository.PessoaRepository;
import com.helpdesksenai.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findByID(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Cliente não encontrado! Id:" + id));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente create (ClienteDTO objDTO){
        objDTO.setId(null);
        validaCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return clienteRepository.save(newObj);
    }

    public Cliente update (Integer id, ClienteDTO objDTO){
        objDTO.setId(id);
        Cliente oldObj = findByID(id);
        if (!objDTO.getSenha().equals(oldObj.getSenha())) {objDTO.setSenha(objDTO.getSenha());}
        validaCpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return clienteRepository.save(oldObj);
    }
    public void delete(Integer id) {
        Cliente obj = findByID(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);


    }

        private void validaCpfEEmail (ClienteDTO objDTO){
            Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
            if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
                throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
            }
            obj = pessoaRepository.findByEmail(objDTO.getEmail());
            if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
                throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
            }
        }

}
