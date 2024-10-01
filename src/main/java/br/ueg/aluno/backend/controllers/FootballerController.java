package br.ueg.aluno.backend.controllers;

import br.ueg.aluno.backend.mappers.FootballerMapper;
import br.ueg.aluno.backend.mappers.GenericMapper;
import br.ueg.aluno.backend.models.FootballerModel;
import br.ueg.aluno.backend.models.dtos.FootballerDTO;
import br.ueg.aluno.backend.services.IFootballerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "${api.version}/back-end/footballer")
public class FootballerController {

    @Autowired
    private IFootballerService service;

    @Autowired
    private GenericMapper mapper;

    @PostMapping
    @Operation(description = "Endpoint para adiconar novos jogadores.")
    public ResponseEntity<Object> create(@Valid @RequestBody FootballerDTO dto) {
        try {
            FootballerModel f = service.create(mapper.map(dto, FootballerModel.class)); //Uso da classe com reflexão aqui!
            return ResponseEntity.ok(f);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().startsWith("ERRO:")) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                return errorMessage(e);
            }
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todos os jogadores.")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.getAll()));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Endpoint para procurar um jogador pelo seu ID.")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.getById(id)));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Endpoint para atualizar os dados de um jogador a partir de seu ID.")
    public ResponseEntity<Object> updateById(
            @PathVariable("id") long id,
            @Valid @RequestBody FootballerDTO dto) {
        try {
            FootballerModel f = mapper.map(dto, FootballerModel.class); //Uso da classe com reflexão aqui!
            f.setId(id);
            return ResponseEntity.of(Optional.ofNullable(service.updateById(f)));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Endpoint para apagar um jogador a partir de seu ID.")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.deleteById(id)));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    public ResponseEntity<Object> errorMessage(Exception e) {
        if (e.getMessage().startsWith("ERRO: Não") || e.getMessage().startsWith("Erro: Um jogador")) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } else {
            return ResponseEntity.internalServerError().body("ERRO: Occorreu um problema no servidor.");
        }
    }
}
