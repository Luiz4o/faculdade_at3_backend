package com.backend.faculdade.repository;

import com.backend.faculdade.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
}
