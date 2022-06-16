package org.barros.modules.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Getter
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

    @ManyToMany(mappedBy = "cursos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas;


}