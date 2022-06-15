package org.barros.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

//    @ManyToOne
//    @JoinColumn(name = "prof_id")
//    private  Professor professor;

}
