package br.com.project.curriculo_aos.controller;

import br.com.project.curriculo_aos.model.HardSkill;
import br.com.project.curriculo_aos.service.HardSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hard_skills")
public class HardSkillController {
    @Autowired
    private HardSkillService hardSkillService;

    @GetMapping
    public List<HardSkill> listarTodasHardSkills() {
        return this.hardSkillService.listarTodasHardSkills();
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

    @DeleteMapping("/{id}")
    public void deletarHardSkill(@PathVariable String id) {
        this.hardSkillService.deletarHardSkill(id);
    }
}
