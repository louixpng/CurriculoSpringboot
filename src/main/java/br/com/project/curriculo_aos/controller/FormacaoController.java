package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.Formacao;
import br.com.project.curriculo_aos.service.FormacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formacoes")
public class FormacaoController {
    @Autowired
    private FormacaoService formacaoService;

    @GetMapping
    public List<Formacao> listarTodasFormacoes () {
        return this.formacaoService.listarTodasFormacoes();
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

    @DeleteMapping("/{id}")
    public void deletarFormacao(@PathVariable String id) {
        this.formacaoService.deletarFormacao(id);
    }

}
