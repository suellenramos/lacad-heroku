package org.barros.modules.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
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
}
