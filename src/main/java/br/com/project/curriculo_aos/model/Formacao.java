package br.com.project.curriculo_aos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name = "formacoes")
public class Formacao {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String idFormacao;
    private String instituicao;
    private String titulo;
    private String anoInicio;
    private String anoConclusao;

    public Formacao () {}

    public Formacao (String idFormacao, String instituicao, String titulo, String anoInicio, String anoConclusao) {
        this.idFormacao = idFormacao;
        this.instituicao = instituicao;
        this.titulo = titulo;
        this.anoInicio = anoInicio;
        this.anoConclusao = anoConclusao;
    }
}
