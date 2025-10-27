package com.backend.faculdade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projeto_funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    public ProjetoFuncionario(Projeto projeto, Funcionario funcionario) {
        this.projeto = projeto;
        this.funcionario = funcionario;
    }
}
