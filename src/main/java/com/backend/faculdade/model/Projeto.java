package com.backend.faculdade.model;

import com.backend.faculdade.dto.projeto.ProjetoResquestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "projetos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private OffsetDateTime dataInicio;
    private OffsetDateTime dataFim;
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProjetoFuncionario> funcionarios = new ArrayList<>();;

    public Projeto(ProjetoResquestDTO dto) {
        this.descricao = dto.descricao();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.funcionarios = new ArrayList<>();
    }

    public void removeFuncionario(Funcionario funcionario){
        this.funcionarios.removeIf(f -> Objects.equals(f.getId(), funcionario.getId()));
    }
}
