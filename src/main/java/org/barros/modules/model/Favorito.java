package org.barros.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "favorito", schema = "public")
public class Favorito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favo_id")
    private Long favoId;

    @ManyToOne
    @JoinColumn(name = "apli_id")
    private Aplicativo aplicativo;

    @ManyToOne
    @JoinColumn(name = "prof_id")
    private Professor professor;
}
