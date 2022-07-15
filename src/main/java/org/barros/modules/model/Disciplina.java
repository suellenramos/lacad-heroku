package org.barros.modules.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "disc_ativo")
    private Boolean ativo = true;

    @ManyToMany(mappedBy = "disciplinas", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Professor> professores;

    @ManyToMany(mappedBy = "disciplinas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Curso> cursos;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "disciplina_conteudo", joinColumns = {@JoinColumn(name = "disc_id")}, inverseJoinColumns = {@JoinColumn(name = "conte_id")})
    private Set<Conteudo> conteudos = new LinkedHashSet<>();
}
