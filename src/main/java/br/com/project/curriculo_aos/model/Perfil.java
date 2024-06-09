package br.com.project.curriculo_aos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @Column(length = 5000)
    private String descricao;

    @JsonManagedReference
    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<Contato>();

    @JsonManagedReference
    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL)
    private List<ExpProfissional> experiencias = new ArrayList<ExpProfissional>();

    @JsonManagedReference
    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL)
    private List<Formacao> formacoes = new ArrayList<Formacao>();

    @JsonManagedReference
    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL)
    private List<HardSkill> habilidades = new ArrayList<HardSkill>();

    public Perfil () {}

    public Perfil (String idPerfil, String nome, int idade, String descricao, List<Contato> contatos, List<ExpProfissional> experiencias, List<Formacao> formacoes, List<HardSkill> habilidades)  {
        this.idPerfil = idPerfil;
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
        this.contatos = contatos;
        this.experiencias = experiencias;
        this.formacoes = formacoes;
        this.habilidades = habilidades;
    }
}
