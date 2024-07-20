package br.com.netdeal.infrastructure.web.controller;

import br.com.netdeal.application.dto.FuncionarioDto;
import br.com.netdeal.application.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> getAllFuncionarios() {
        return ResponseEntity.ok(funcionarioService.getAllFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDto> getFuncionarioById(@PathVariable Long id) {
        FuncionarioDto Funcionario = funcionarioService.getFuncionarioById(id);
        if (Funcionario != null) {
            return ResponseEntity.ok(Funcionario);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lideranca/{id}")
    public ResponseEntity<List<FuncionarioDto>> getFuncionariosByLiderancaId(@PathVariable Long id) {
        FuncionarioDto funcionario = funcionarioService.getFuncionarioById(id);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionarioService.getFuncionariosByLiderancaId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<FuncionarioDto>> getFuncionariosByNivel(@PathVariable Long nivel) {
            return ResponseEntity.ok(funcionarioService.getFuncionariosByCargoNivel(nivel));
    }

    @PostMapping
    public FuncionarioDto createFuncionario(@RequestBody FuncionarioDto funcionarioDto) {
        return funcionarioService.createFuncionario(funcionarioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDto> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDto) {
        FuncionarioDto updatedFuncionario = funcionarioService.updateFuncionario(id, funcionarioDto);
        if (updatedFuncionario != null) {
            return ResponseEntity.ok(updatedFuncionario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
