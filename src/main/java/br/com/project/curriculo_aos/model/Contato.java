package br.com.project.curriculo_aos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table (name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idContato;
    //Especifica o tipo de contato para ser cadastrado (Se é telefone, email, behance, linkedin, etc.)
    private String tipoContato;
    //Especifica o respectivo valor do contato (Telefone: "81 99827-2917, fulano@gmail.com, etc.)
    private String valorContato;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;

    public Contato () {}

    public Contato (String idContato, String tipoContato, String valorContato, Perfil perfil) {
        this.idContato = idContato;
        this.tipoContato = tipoContato;
        this.valorContato = valorContato;
        this.perfil = perfil;
    }

}
