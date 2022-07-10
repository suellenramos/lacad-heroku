package org.barros.modules.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "curso_disciplina", schema = "public")
public class CursoDisciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disc_id")
    private   Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "cur_id")
    private   Curso curso;

    @ManyToOne
    @JoinColumn(name = "prof_id")
    private   Professor professor;

    @OneToMany(mappedBy = "cursoDisciplina")
    private List<DisciplinaConteudo> disciplinaConteudos ;
}
