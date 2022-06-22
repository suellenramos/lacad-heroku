package org.barros.modules.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disciplina", schema = "public")
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disc_id")
    private Long discId;

    @Column(name = "disc_descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "prof_id")
    private  Professor professor;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "curso_disciplina",
            joinColumns = @JoinColumn(name = "disc_id"),
            inverseJoinColumns = @JoinColumn(name = "cur_id"))
    private List<Curso> cursos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "disciplina_conteudo",
            joinColumns = @JoinColumn(name = "disc_id"),
            inverseJoinColumns = @JoinColumn(name = "conte_id"))
    private List<Conteudo> conteudos;
}
