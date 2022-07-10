package org.barros.modules.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
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

    @OneToMany(mappedBy = "disciplina")
    private List<CursoDisciplina> cursoDisciplinas ;

    @OneToMany
    @JoinColumn(name = "disc_id")
    private Set<Disciplina> disciplinas = new LinkedHashSet<>();

}
