package com.backend.faculdade.service;

import com.backend.faculdade.dto.funcionario.FuncionarioRequestDTO;
import com.backend.faculdade.dto.funcionario.FuncionarioResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResponseSemFuncionarioDTO;
import com.backend.faculdade.dto.setor.SetorRequestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;

import java.util.List;

public interface FuncionarioService {

    FuncionarioResponseDTO findById(Long id);

    List<FuncionarioResponseDTO> findAll();

    FuncionarioResponseDTO create(FuncionarioRequestDTO funcionarioRequestDTO);

    FuncionarioResponseDTO update(Long id,FuncionarioRequestDTO funcionarioRequestDTO);

    void delete(Long id);

    List<ProjetoResponseSemFuncionarioDTO> buscarProjetos(Long idFuncionario);

}
