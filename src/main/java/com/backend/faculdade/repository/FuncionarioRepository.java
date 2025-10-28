package com.backend.faculdade.repository;

import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    @Query("""
        SELECT DISTINCT p 
        FROM Projeto p
        JOIN p.funcionarios pf
        JOIN pf.funcionario f
        WHERE f.id = :idFuncionario
    """)
    List<Projeto> findProjetosByFuncionarioId(@Param("idFuncionario") Long idFuncionario);
}
