package com.backend.faculdade.controler;

import com.backend.faculdade.dto.projeto.ProjetoResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResquestDTO;
import com.backend.faculdade.dto.setor.SetorRequestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;
import com.backend.faculdade.service.impl.ProjetoServiceImpl;
import com.backend.faculdade.service.impl.SetorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(name = "/api/projeto")
public class ProjetoController {

    // FALTA COLOCAR UPDATE NO PROJETO LUIZ PO

    private final ProjetoServiceImpl projetoService;

    public ProjetoController(ProjetoServiceImpl projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> getById(@PathVariable(name = "id") Long id){
        var projeto = projetoService.findById(id);

        return ResponseEntity.ok().body(projeto);
    }

    @PostMapping()
    public ResponseEntity<ProjetoResponseDTO> create(@RequestBody ProjetoResquestDTO dto){
        var projeto = projetoService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projeto.id())
                .toUri();
        return ResponseEntity.created(uri).body(projeto);
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponseDTO>> getAll(){
        var listProjeto = projetoService.findAll();
        return  ResponseEntity.ok().body(listProjeto);
    }

    @PostMapping("/{id}/funcionario")
    public ResponseEntity<ProjetoResponseDTO> addFuncionario(@PathVariable(name = "id") Long id, @RequestBody Long idFuncionario){
        var setor = projetoService.addFuncionario(id,idFuncionario);
        return ResponseEntity.ok().body(setor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        projetoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/funcionario")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable(name = "id") Long id, @RequestBody Long idFuncionario){
        projetoService.deleteFuncionario(id,idFuncionario);
        return ResponseEntity.noContent().build();
    }
}
