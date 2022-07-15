package org.barros.modules.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "conteudo", schema = "public")
public class Conteudo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conte_id")
    private Long conteId;

    @Column(name = "conte_descricao")
    private String descricao;

    @Column(name = "conte_ativo")
    private Boolean ativo = true;

    @ManyToMany(mappedBy = "conteudos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "conteudo")
    private List<ConteudoAplicativo> conteudoAplicativos;
}
