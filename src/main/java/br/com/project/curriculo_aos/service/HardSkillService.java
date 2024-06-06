package br.com.project.curriculo_aos.service;

import br.com.project.curriculo_aos.model.HardSkill;
import br.com.project.curriculo_aos.repository.HardSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class HardSkillService {
    @Autowired
    private HardSkillRepository hardSkillRepository;

    public List<HardSkill> listarTodasHardSkills () {
        return this.hardSkillRepository.findAll();
    }

    public Optional<HardSkill> buscarHardSkillPorId (String id){
        return this.hardSkillRepository.findById(id);
    }

    public HardSkill criarHardSkill (HardSkill hardSkill) {
        return this.hardSkillRepository.save(hardSkill);
    }

    public void deletarHardSkill (String id) {
        this.hardSkillRepository.deleteById(id);
    }

    public boolean existePorId (String id) {
        return this.hardSkillRepository.existsById(id);
    }
}
