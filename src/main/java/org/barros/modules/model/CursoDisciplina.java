package org.barros.modules.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "curso_disciplina")
public class CursoDisciplina implements Serializable {

    @Id
    @Column(name = "cd_id")
    private Long cDid;
    @ManyToOne
    @JoinColumn(name = "disc_id")
    private   Disciplina disciplina;
    @ManyToOne
    @JoinColumn(name = "cur_id")
    private   Curso curso;
}
