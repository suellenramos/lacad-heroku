package org.barros.modules.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<Disciplina> disciplinas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "professor_aplicativo",
            joinColumns = @JoinColumn(name = "prof_id"),
            inverseJoinColumns = @JoinColumn(name = "apli_id"))
    private List<Aplicativo> aplicativos;

    @OneToMany(mappedBy = "professor")
    private List<Avaliacao> avaliacoes;

}
