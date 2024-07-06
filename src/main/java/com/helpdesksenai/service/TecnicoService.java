package com.helpdesksenai.service;

import com.helpdesksenai.DTO.TecnicoDTO;
import com.helpdesksenai.entity.Pessoa;
import com.helpdesksenai.entity.Tecnico;
import com.helpdesksenai.exceptions.ObjectNotFoundException;
import com.helpdesksenai.repository.PessoaRepository;
import com.helpdesksenai.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findByID(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Tecnico não encontrado! Id:" + id));
    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    public Tecnico create (TecnicoDTO objDTO){
        objDTO.setId(null);
        validaCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return tecnicoRepository.save(newObj);
    }

    public Tecnico update (Integer id, TecnicoDTO objDTO){
        objDTO.setId(id);
        Tecnico oldObj = findByID(id);
        if (!objDTO.getSenha().equals(oldObj.getSenha())) {objDTO.setSenha(objDTO.getSenha());}
        validaCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return tecnicoRepository.save(oldObj);
    }
    public void delete(Integer id) {
        Tecnico obj = findByID(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);


    }

        private void validaCpfEEmail (TecnicoDTO objDTO){
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
