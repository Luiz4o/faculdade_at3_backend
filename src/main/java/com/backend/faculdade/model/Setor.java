package com.backend.faculdade.model;

import com.backend.faculdade.dto.setor.SetorRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Table(name = "setores")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "setor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    public Setor(SetorRequestDTO dto) {
        this.nome = dto.nome();
    }

    public void addFuncionario(Funcionario funcionario){
        this.funcionarios.add(funcionario);
    }

    public void removeFuncionario(Funcionario funcionario){
        this.funcionarios.removeIf(f -> Objects.equals(f.getId(), funcionario.getId()));
    }
}
