package org.barros.modules.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "curso", schema = "public")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cur_id")
    private Long curId;

    @Column(name = "cur_descricao")
    private String descricao;

    @Column(name = "cur_ativo")
    private Boolean ativo = true;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "curso_disciplina", joinColumns = {@JoinColumn(name = "cur_id")}, inverseJoinColumns = {@JoinColumn(name = "disc_id")})
    private Set<Disciplina> disciplinas = new LinkedHashSet<>();

}

