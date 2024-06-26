package br.com.project.curriculo_aos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "exp_profissionais")
public class ExpProfissional {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idExpProfissional;
    private String empresa;
    private String cargo;
    private String descricao;
    private int anoInicio;
    private int anoConclusao;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;

    public ExpProfissional () {}

    public ExpProfissional(String idExpProfissional, String empresa, String cargo, String descricao, int anoInicio, int anoConclusao, Perfil perfil) {
        this.idExpProfissional = idExpProfissional;
        this.empresa = empresa;
        this.cargo = cargo;
        this.descricao = descricao;
        this.anoInicio = anoInicio;
        this.anoConclusao = anoConclusao;
        this.perfil = perfil;
    }

}
