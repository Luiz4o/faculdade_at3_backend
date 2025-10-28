package com.backend.faculdade.controler;

import com.backend.faculdade.dto.funcionario.FuncionarioRequestDTO;
import com.backend.faculdade.dto.funcionario.FuncionarioResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResponseDTO;
import com.backend.faculdade.service.impl.FuncionarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    private final FuncionarioServiceImpl funcionarioService;

    public FuncionarioController(FuncionarioServiceImpl funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> getById(@PathVariable(name = "id") Long id){
        var funcionario = funcionarioService.findById(id);

        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> getAll(){
        var funcionarios = funcionarioService.findAll();

        return ResponseEntity.ok().body(funcionarios);
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> create(@RequestBody FuncionarioRequestDTO dto){
        var funcionario = funcionarioService.create(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionario.id())
                .toUri();

        return ResponseEntity.created(uri).body(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> update(@PathVariable(name = "id") Long id,@RequestBody FuncionarioRequestDTO dto){
        var funcionario = funcionarioService.update(id, dto);

        return ResponseEntity.ok().body(funcionario);
    }

    @DeleteMapping("/{ìd}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        funcionarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/projetos")
    public ResponseEntity<List<ProjetoResponseDTO>> buscarProjetos(@PathVariable Long id) {
        var projetos = funcionarioService.buscarProjetos(id);
        return ResponseEntity.ok(projetos);
    }
}
