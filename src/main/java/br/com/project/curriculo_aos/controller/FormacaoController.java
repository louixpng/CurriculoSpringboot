package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.Formacao;
import br.com.project.curriculo_aos.model.Perfil;
import br.com.project.curriculo_aos.service.FormacaoService;
import br.com.project.curriculo_aos.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formacoes")
public class FormacaoController {
    @Autowired
    private FormacaoService formacaoService;
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<Formacao> listarTodasFormacoes () {
        return this.formacaoService.listarTodasFormacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formacao> buscarFormacaoPorId(@PathVariable String id) {
        Optional<Formacao> formacao = this.formacaoService.buscarFormacaoPorId(id);
        if(formacao.isPresent()){
            return ResponseEntity.ok(formacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Formacao criarFormacao(@RequestBody Formacao formacao) {
        return this.formacaoService.criarFormacao(formacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formacao> atualizarFormacao (@PathVariable String id, @RequestBody Formacao formacaoDetalhes) {
        Optional<Formacao> formacao = this.formacaoService.buscarFormacaoPorId(id);
        if (formacao.isPresent()) {
            Formacao formacaoAtualizada = formacao.get();
            formacaoAtualizada.setInstituicao(formacaoDetalhes.getInstituicao());
            formacaoAtualizada.setTitulo(formacaoDetalhes.getTitulo());
            formacaoAtualizada.setAnoInicio(formacaoDetalhes.getAnoInicio());
            formacaoAtualizada.setAnoConclusao(formacaoDetalhes.getAnoConclusao());
            return ResponseEntity.ok(this.formacaoService.criarFormacao(formacaoAtualizada));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/{idPerfil}")
    public ResponseEntity<Perfil> atribuirPerfil(@PathVariable String id, @PathVariable String idPerfil) {
        Optional<Perfil> perfil = this.perfilService.buscarPerfilPorId(idPerfil);
        Optional<Formacao> formacao = this.formacaoService.buscarFormacaoPorId(id);

        if (perfil.isPresent()){
            if (formacao.isPresent()){
                Perfil p = perfil.get();
                Formacao f = formacao.get();
                List<Formacao> formacaoList = new ArrayList<>();
                formacaoList.add(f);
                p.setFormacoes(formacaoList);
                f.setPerfil(p);
                this.formacaoService.criarFormacao(f);
                return ResponseEntity.ok(this.perfilService.criarPerfil(p));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarFormacao(@PathVariable String id) {
        this.formacaoService.deletarFormacao(id);
    }

}
