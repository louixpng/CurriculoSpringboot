package br.com.project.curriculo_aos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name = "perfis")
public class Perfil {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String idPerfil;
    private String nome;
    private int idade;
    private String descricao;

    public Perfil () {}

    public Perfil (String idPerfil, String nome, int idade, String descricao) {
        this.idPerfil = idPerfil;
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
    }
}
