package org.barros.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

//    @OneToMany(mappedBy = "aplicativo")
//    private List<ConteudoAplicativo> conteudoAplicativos;
//
//    @OneToMany(mappedBy = "aplicativo")
//    private List<ProfessorAplicativo> professorAplicativos;

    @OneToMany(mappedBy = "aplicativo")
    private List<Avaliacao> avaliacoes;

    @OneToMany
    @JoinColumn(name = "apli_id")
    private Set<Aplicativo> aplicativos = new LinkedHashSet<>();
}
