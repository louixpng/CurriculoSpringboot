package br.com.project.curriculo_aos.service;

import br.com.project.curriculo_aos.model.Formacao;
import br.com.project.curriculo_aos.repository.FormacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormacaoService {
    @Autowired
    private FormacaoRepository formacaoRepository;

    public List<Formacao> listarTodasFormacoes() {
        return this.formacaoRepository.findAll();
    }

    public Optional<Formacao> buscarFormacaoPorId (String id) {
        return this.formacaoRepository.findById(id);
    }

    public Formacao criarFormacao (Formacao formacao) {
        return this.formacaoRepository.save(formacao);
    }

    public void deletarFormacao (String id) {
        this.formacaoRepository.deleteById(id);
    }

    public boolean existePorId (String id) {
        return this.formacaoRepository.existsById(id);
    }
}
