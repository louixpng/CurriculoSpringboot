package br.com.project.curriculo_aos.service;

import br.com.project.curriculo_aos.model.Perfil;
import br.com.project.curriculo_aos.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> listarTodosPerfis() {
        return this.perfilRepository.findAll();
    }

    public Optional<Perfil> buscarPerfilPorId(String id) {
        return this.perfilRepository.findById(id);
    }

    public Perfil criarPerfil (Perfil perfil) {
        return this.perfilRepository.save(perfil);
    }

    public void deletarPerfil (String id) {
        this.perfilRepository.deleteById(id);
    }

    public boolean existePorId (String id) {
        return this.perfilRepository.existsById(id);
    }
}
