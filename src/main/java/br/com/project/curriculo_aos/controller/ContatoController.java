package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.Contato;
import br.com.project.curriculo_aos.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<Contato> buscarTodosContatos () {
        return this.contatoService.listarTodosContatos();
    }

    @PostMapping
    public Contato criarContato(@RequestBody Contato contato) {
        return this.contatoService.criarContato(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable String id, @RequestBody Contato contatoDetalhes) {
        Optional<Contato> contato = this.contatoService.buscarContatoPorId(id);
        if (contato.isPresent()) {
            Contato contatoAtualizado = contato.get();
            contatoAtualizado.setTipoContato(contatoDetalhes.getTipoContato());
            contatoAtualizado.setValorContato(contatoDetalhes.getValorContato());
            return ResponseEntity.ok(this.contatoService.criarContato(contatoAtualizado));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable String id) {
        this.contatoService.deletarContatoPorId(id);
    }
}
