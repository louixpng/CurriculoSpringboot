package br.com.project.curriculo_aos.service;

import br.com.project.curriculo_aos.model.Contato;
import br.com.project.curriculo_aos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    public List<Contato> listarTodosContatos() {
        return this.contatoRepository.findAll();
    }

    public Optional<Contato> buscarContatoPorId(String id) {
        return this.contatoRepository.findById(id);
    }

    public Contato criarContato (Contato contato) {
        return this.contatoRepository.save(contato);
    }

    public void deletarContatoPorId (String id) {
        this.contatoRepository.deleteById(id);
    }

    public boolean existePorId (String id) {
        return this.contatoRepository.existsById(id);
    }
}
