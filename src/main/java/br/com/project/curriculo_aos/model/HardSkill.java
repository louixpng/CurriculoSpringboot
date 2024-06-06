package br.com.project.curriculo_aos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name = "hard_skills")
public class HardSkill {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String idHardSkill;
    private String ferramenta;
    private String nivel;

    public HardSkill () {}

    public HardSkill (String idHardSkill, String ferramenta, String nivel) {
        this.idHardSkill = idHardSkill;
        this.ferramenta = ferramenta;
        this.nivel = nivel;
    }
}
