package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.Contato;
import br.com.project.curriculo_aos.model.Perfil;
import br.com.project.curriculo_aos.service.ContatoService;
import br.com.project.curriculo_aos.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<Contato> buscarTodosContatos () {
        return this.contatoService.listarTodosContatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarContatoPorId(@PathVariable String id) {
        Optional<Contato> contato = this.contatoService.buscarContatoPorId(id);
        if(contato.isPresent()){
            return ResponseEntity.ok(contato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PutMapping("/{id}/{idPerfil}")
    public ResponseEntity<Perfil> atribuirPerfil(@PathVariable String id, @PathVariable String idPerfil) {
        Optional<Perfil> perfil = this.perfilService.buscarPerfilPorId(idPerfil);
        Optional<Contato> contato = this.contatoService.buscarContatoPorId(id);

        if (perfil.isPresent()) {
            if (contato.isPresent()){
                Perfil p = perfil.get();
                Contato c = contato.get();
                List<Contato> contatoList = new ArrayList<>();
                contatoList.add(c);
                p.setContatos(contatoList);
                c.setPerfil(p);
                this.contatoService.criarContato(c);
                return ResponseEntity.ok(this.perfilService.criarPerfil(p));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable String id) {
        this.contatoService.deletarContatoPorId(id);
    }
}
