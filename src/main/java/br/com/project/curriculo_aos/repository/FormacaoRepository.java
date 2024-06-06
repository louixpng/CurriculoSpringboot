package br.com.project.curriculo_aos.repository;

import br.com.project.curriculo_aos.model.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormacaoRepository extends JpaRepository<Formacao, String> {
}
