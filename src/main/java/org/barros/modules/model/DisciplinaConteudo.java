package org.barros.modules.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @JoinColumn(name = "cd_id")
    private   CursoDisciplina cursoDisciplina;

//    @ManyToOne
//    @JoinColumn(name = "conte_id")
//    private   Conteudo conteudo;

    @OneToMany(mappedBy = "disciplinaConteudo")
    private List<ConteudoAplicativo> conteudoAplicativos ;
}
