package org.barros.modules.model;

import lombok.Data;
import lombok.Getter;

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

    @OneToMany(mappedBy = "curso")
    private List<CursoDisciplina> cursoDisciplinas;

    @OneToMany
    @JoinColumn(name = "cur_id")
    private Set<Imagem> imagens = new LinkedHashSet<>();

}

