package org.barros.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "professor", schema = "public")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id")
    private Long profId;

    @Column(name = "prof_nome")
    private String nome;

    @Column(name = "prof_email")
    private String email;

    @Column(name = "prof_senha")
    private String senha;

    @OneToMany(mappedBy = "professor")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "professor")
    private List<CursoDisciplina> cursoDisciplinas;
}
