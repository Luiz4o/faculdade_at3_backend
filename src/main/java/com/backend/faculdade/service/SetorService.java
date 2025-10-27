package com.backend.faculdade.service;

import com.backend.faculdade.dto.setor.SetorRequestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;
import com.backend.faculdade.model.Setor;

import java.util.List;

public interface SetorService {

    SetorResponseDTO findById(Long id);

    List<SetorResponseDTO> findAll();

    SetorResponseDTO create(SetorRequestDTO setorRequestDTO);

    SetorResponseDTO addFuncionario(Long idSetor, Long idFuncionario);

    SetorResponseDTO addListFuncionarios(Long idSetor, List<Long> idFuncionarios);

    void deleteFuncionario(Long idSetor, Long idFuncionario);

    void deleteListFuncionario(Long idSetor, List<Long> idFuncionarios);

    SetorResponseDTO update(SetorRequestDTO setorRequestDTO);

    void delete(Long id);
}
