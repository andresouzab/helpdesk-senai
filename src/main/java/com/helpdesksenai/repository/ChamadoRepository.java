package com.helpdesksenai.repository;

import com.helpdesksenai.entity.Chamado;
import com.helpdesksenai.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
