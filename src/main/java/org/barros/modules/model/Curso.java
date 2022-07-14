package org.barros.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

//    @OneToMany(mappedBy = "curso")
//    private List<CursoDisciplina> cursoDisciplinas;

}

