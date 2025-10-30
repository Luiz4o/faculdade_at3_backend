package com.backend.faculdade.controler;

import com.backend.faculdade.dto.funcionario.FuncionarioIdDTO;
import com.backend.faculdade.dto.setor.SetorRequestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;
import com.backend.faculdade.service.impl.SetorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/setor")
public class SetorController {
    private final SetorServiceImpl setorService;

    public SetorController(SetorServiceImpl setorService) {
        this.setorService = setorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> getById(@PathVariable(name = "id") Long id){
        var setor = setorService.findById(id);

        return ResponseEntity.ok().body(setor);
    }

    @PostMapping()
    public ResponseEntity<SetorResponseDTO> create(@RequestBody SetorRequestDTO dto){
        var setor =setorService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(setor.id())
                .toUri();
        return ResponseEntity.created(uri).body(setor);
    }

    @GetMapping
    public ResponseEntity<List<SetorResponseDTO>> getAll(){
        var listSetor = setorService.findAll();
        return  ResponseEntity.ok().body(listSetor);
    }

    @PostMapping("/{id}/funcionario")
    public ResponseEntity<SetorResponseDTO> addFuncionario(@PathVariable(name = "id") Long id, @RequestBody FuncionarioIdDTO dto){
        var setor = setorService.addFuncionario(id,dto.idFuncionario());
        return ResponseEntity.ok().body(setor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        setorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/funcionario")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable(name = "id") Long id, @RequestBody FuncionarioIdDTO dto){
        setorService.deleteFuncionario(id,dto.idFuncionario());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> update(
            @PathVariable Long id,
            @RequestBody SetorRequestDTO dto
    ) {
        var atualizado = setorService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}
