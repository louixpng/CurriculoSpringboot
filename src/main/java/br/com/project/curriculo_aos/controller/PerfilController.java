package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.Perfil;
import br.com.project.curriculo_aos.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<Perfil> listarTodosPerfis() {
        return this.perfilService.listarTodosPerfis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable String id) {
        Optional<Perfil> perfil = this.perfilService.buscarPerfilPorId(id);
        if(perfil.isPresent()){
            return ResponseEntity.ok(perfil.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Perfil criarPerfil (@RequestBody Perfil perfil) {
        return this.perfilService.criarPerfil(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable String id, @RequestBody Perfil perfilDetalhes) {
        Optional<Perfil> perfil = this.perfilService.buscarPerfilPorId(id);
        if (perfil.isPresent()){
            Perfil perfilAtualizado = perfil.get();
            perfilAtualizado.setNome(perfilDetalhes.getNome());
            perfilAtualizado.setIdade(perfilDetalhes.getIdade());
            perfilAtualizado.setDescricao(perfilDetalhes.getDescricao());
            return ResponseEntity.ok(this.perfilService.criarPerfil(perfilAtualizado));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarPerfil(@PathVariable String id) {
        this.perfilService.deletarPerfil(id);
    }
}
