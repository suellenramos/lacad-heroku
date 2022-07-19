package org.barros.modules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "professor", schema = "public")
public class Professor implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id", nullable = false)
    private Long profId;

    @Column(name = "prof_nome")
    private String nome;

    @CreationTimestamp
    @Column(name = "prof_data_hora_cadastro")
    private LocalDateTime dataInsercao = LocalDateTime.now();

    @Column(name = "prof_email")
    @Email
    private String email;

    @Column(name = "prof_password")
    private String password;

    @Column(name = "prof_ativo")
    private Boolean ativo = true;

    @OneToMany(mappedBy = "professor")
    private List<Avaliacao> avaliacoes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "professor_curso", joinColumns = {@JoinColumn(name = "prof_id")}, inverseJoinColumns = {@JoinColumn(name = "cur_id")})
    private Set<Curso> cursos = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "professor_disciplina", joinColumns = {@JoinColumn(name = "prof_id")}, inverseJoinColumns = {@JoinColumn(name = "disc_id")})
    private Set<Disciplina> disciplinas = new LinkedHashSet<>();

    @JsonIgnoreProperties({"professores"})
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "PROFESSOR_PERFIL",
            joinColumns = @JoinColumn(name = "PROF_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERF_ID")
    )
    private List<Perfil> perfils = new ArrayList<Perfil>();

}
