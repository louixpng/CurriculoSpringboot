package br.com.project.curriculo_aos.repository;

import br.com.project.curriculo_aos.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, String> {
}
