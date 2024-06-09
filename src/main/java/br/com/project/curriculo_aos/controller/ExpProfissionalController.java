package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.ExpProfissional;
import br.com.project.curriculo_aos.model.Perfil;
import br.com.project.curriculo_aos.service.ExpProfissionalService;
import br.com.project.curriculo_aos.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exp_profissionais")
public class ExpProfissionalController {
    @Autowired
    private ExpProfissionalService expProfissionalService;
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<ExpProfissional> listarTodasExpProfissionais() {
        return this.expProfissionalService.listarTodasExpProfissionais();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpProfissional> buscarExpProfissionalPorId(@PathVariable String id) {
        Optional<ExpProfissional> expProfissional = this.expProfissionalService.buscarExpProfissionalPorId(id);
        if(expProfissional.isPresent()){
            return ResponseEntity.ok(expProfissional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PutMapping("/{id}/{idPerfil}")
    public ResponseEntity<Perfil> atribuirPerfil(@PathVariable String id, @PathVariable String idPerfil) {
        Optional<Perfil> perfil = this.perfilService.buscarPerfilPorId(idPerfil);
        Optional<ExpProfissional> expProfissional = this.expProfissionalService.buscarExpProfissionalPorId(id);

        if (perfil.isPresent()) {
            if (expProfissional.isPresent()) {
                Perfil p = perfil.get();
                ExpProfissional exp = expProfissional.get();
                List<ExpProfissional> expProfissionalList = new ArrayList<>();
                expProfissionalList.add(exp);
                p.setExperiencias(expProfissionalList);
                exp.setPerfil(p);
                this.expProfissionalService.criarExpProfissional(exp);
                return ResponseEntity.ok(this.perfilService.criarPerfil(p));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarExpProfissional (@PathVariable String id) {
        this.expProfissionalService.deletarExpProfissional(id);
    }
}
