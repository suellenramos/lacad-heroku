package org.barros.modules.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
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

    @ManyToMany(mappedBy = "conteudos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas;

    @ManyToMany
    @JoinTable(name = "conteudo_aplicativo",
            joinColumns = @JoinColumn(name = "conte_id"),
            inverseJoinColumns = @JoinColumn(name = "apli_id"))
    private List<Aplicativo> aplicativos;

}