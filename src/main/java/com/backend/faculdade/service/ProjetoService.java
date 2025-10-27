package com.backend.faculdade.service;

import com.backend.faculdade.dto.projeto.ProjetoResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResquestDTO;
import com.backend.faculdade.dto.setor.SetorRequestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;

import java.util.List;

public interface ProjetoService {
    ProjetoResponseDTO findById(Long id);

    List<ProjetoResponseDTO> findAll();

    ProjetoResponseDTO create(ProjetoResquestDTO projetoResquestDTO);

    ProjetoResponseDTO addFuncionario(Long idProjeto, Long idFuncionario);

    ProjetoResponseDTO addListFuncionarios(Long idProjeto, List<Long> idFuncionarios);

    void deleteFuncionario(Long idProjeto, Long idFuncionario);

    void deleteListFuncionario(Long idProjeto, List<Long> idFuncionarios);

    ProjetoResponseDTO update(ProjetoResquestDTO projetoRequestDTO);

    void delete(Long id);
}
