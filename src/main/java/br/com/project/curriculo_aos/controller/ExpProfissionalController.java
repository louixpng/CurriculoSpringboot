package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.ExpProfissional;
import br.com.project.curriculo_aos.service.ExpProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exp_profissionais")
public class ExpProfissionalController {
    @Autowired
    private ExpProfissionalService expProfissionalService;

    @GetMapping
    public List<ExpProfissional> listarTodasExpProfissionais() {
        return this.expProfissionalService.listarTodasExpProfissionais();
    }

    @PostMapping
    public ExpProfissional criarExpProfissional(@RequestBody ExpProfissional expProfissional) {
        return this.expProfissionalService.criarExpProfissional(expProfissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpProfissional> atualizarExpProfissional(@PathVariable String id, @RequestBody ExpProfissional expProfissionalDetalhes) {
        Optional<ExpProfissional> expProfissional = this.expProfissionalService.buscarExpProfissionalPorId(id);
        if (expProfissional.isPresent()) {
            ExpProfissional expProfissionalAtualizada = expProfissional.get();
            expProfissionalAtualizada.setCargo(expProfissionalDetalhes.getCargo());
            expProfissionalAtualizada.setEmpresa(expProfissionalDetalhes.getEmpresa());
            expProfissionalAtualizada.setDescricao(expProfissionalDetalhes.getDescricao());
            expProfissionalAtualizada.setAnoInicio(expProfissionalDetalhes.getAnoInicio());
            expProfissionalAtualizada.setAnoConclusao(expProfissionalDetalhes.getAnoConclusao());
            return ResponseEntity.ok(this.expProfissionalService.criarExpProfissional(expProfissionalAtualizada));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarExpProfissional (@PathVariable String id) {
        this.expProfissionalService.deletarExpProfissional(id);
    }
}
