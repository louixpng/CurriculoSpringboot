package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.HardSkill;
import br.com.project.curriculo_aos.model.Perfil;
import br.com.project.curriculo_aos.service.HardSkillService;
import br.com.project.curriculo_aos.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hard_skills")
public class HardSkillController {
    @Autowired
    private HardSkillService hardSkillService;
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<HardSkill> listarTodasHardSkills() {
        return this.hardSkillService.listarTodasHardSkills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HardSkill> buscarHardSkillPorId(@PathVariable String id) {
        Optional<HardSkill> hardSkill = this.hardSkillService.buscarHardSkillPorId(id);
        if(hardSkill.isPresent()){
            return ResponseEntity.ok(hardSkill.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public HardSkill criarHardSkill(@RequestBody HardSkill hardSkill) {
        return this.hardSkillService.criarHardSkill(hardSkill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HardSkill> atualizarHardSkill(@PathVariable String id, @RequestBody HardSkill hardSkillDetalhes) {
        Optional<HardSkill> hardSkill = this.hardSkillService.buscarHardSkillPorId(id);
        if (hardSkill.isPresent()) {
            HardSkill hardSkillAtualizada = hardSkill.get();
            hardSkillAtualizada.setFerramenta(hardSkillDetalhes.getFerramenta());
            hardSkillAtualizada.setNivel(hardSkillDetalhes.getNivel());
            return ResponseEntity.ok(this.hardSkillService.criarHardSkill(hardSkillAtualizada));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/{idPerfil}")
    public ResponseEntity<Perfil> atribuirPerfil(@PathVariable String id, @PathVariable String idPerfil) {
        Optional<Perfil> perfil = this.perfilService.buscarPerfilPorId(idPerfil);
        Optional<HardSkill> hardSkill = this.hardSkillService.buscarHardSkillPorId(id);

        if (perfil.isPresent()) {
            if (hardSkill.isPresent()) {
                Perfil p = perfil.get();
                HardSkill h = hardSkill.get();
                List<HardSkill> hardSkillList = new ArrayList<>();
                hardSkillList.add(h);
                p.setHabilidades(hardSkillList);
                h.setPerfil(p);
                this.hardSkillService.criarHardSkill(h);
                return ResponseEntity.ok(this.perfilService.criarPerfil(p));
            } else {
               return ResponseEntity.notFound().build();
            }
        } else {
          return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarHardSkill(@PathVariable String id) {
        this.hardSkillService.deletarHardSkill(id);
    }
}
