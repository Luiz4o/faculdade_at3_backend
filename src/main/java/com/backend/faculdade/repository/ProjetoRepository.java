package com.backend.faculdade.repository;

import com.backend.faculdade.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    @Query("""
        SELECT DISTINCT p 
        FROM Projeto p
        LEFT JOIN FETCH p.funcionarios pf
        LEFT JOIN FETCH pf.funcionario f
        WHERE p.id = :id
    """)
    Optional<Projeto> findProjetoComFuncionarios(@Param("id") Long id);

    List<Projeto> findByDataInicioBetween(OffsetDateTime dataInicio, OffsetDateTime dataFim);

}
