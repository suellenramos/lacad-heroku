package org.barros.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "aplicativo", schema = "public")
public class Aplicativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apli_id")
    private Long apliId;

    @Column(name = "apli_link")
    private String link;

    @Column(name = "apli_descricao")
    private String descricao;

    @OneToMany(mappedBy = "aplicativo")
    private List<ConteudoAplicativo> conteudoAplicativos;

//    @ManyToMany(mappedBy = "aplicativos")
//    private List<Professor> professores;


    @OneToMany(mappedBy = "aplicativo")
    private List<Avaliacao> avaliacoes;
}
