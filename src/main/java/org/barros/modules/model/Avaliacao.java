package org.barros.modules.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "avaliacao", schema = "public")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ava_id")
    private Long avaId;

    @Column(name = "ava_nota")
    private String nota;

    @Column(name = "ava_comentarios")
    private String comentarios;
//
//    @ManyToOne
//    @JoinColumn(name = "prof_id")
//    private  Professor professor;
//
//    @ManyToOne
//    @JoinColumn(name = "apli_id")
//    private  Aplicativo aplicativo;
}
