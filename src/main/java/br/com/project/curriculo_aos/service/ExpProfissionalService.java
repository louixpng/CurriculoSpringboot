package br.com.project.curriculo_aos.service;

import br.com.project.curriculo_aos.model.ExpProfissional;
import br.com.project.curriculo_aos.repository.ExpProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ExpProfissionalService {
    @Autowired
    private ExpProfissionalRepository expProfissionalRepository;

    public List<ExpProfissional> listarTodasExpProfissionais(){
        return this.expProfissionalRepository.findAll();
    }

    public Optional<ExpProfissional> buscarExpProfissionalPorId(String id){
        return this.expProfissionalRepository.findById(id);
    }

    public ExpProfissional criarExpProfissional (ExpProfissional expProfissional) {
        return this.expProfissionalRepository.save(expProfissional);
    }

    public void deletarExpProfissional (String id) {
        this.expProfissionalRepository.deleteById(id);
    }

    public boolean existePorId (String id) {
        return this.expProfissionalRepository.existsById(id);
    }
}
