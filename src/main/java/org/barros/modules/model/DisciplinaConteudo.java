package org.barros.modules.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "disciplina_conteudo", schema = "public")
public class DisciplinaConteudo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dc_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disc_id")
    private   Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "conte_id")
    private   Conteudo conteudo;
}
