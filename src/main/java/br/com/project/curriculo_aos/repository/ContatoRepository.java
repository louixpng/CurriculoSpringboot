package br.com.project.curriculo_aos.repository;

import br.com.project.curriculo_aos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, String> {
}
