package com.backend.faculdade.repository;

import com.backend.faculdade.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor,Long> {
    @Query("""
        SELECT DISTINCT s
        FROM Setor s
        LEFT JOIN FETCH s.funcionarios f
    """)
    List<Setor> findAllWithFuncionarios();
}
