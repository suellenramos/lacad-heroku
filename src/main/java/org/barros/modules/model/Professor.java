package org.barros.modules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "professor")
    private List<CursoDisciplina> cursoDisciplinas;
    @JsonIgnoreProperties({"professores"})
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PROFESSOR_PERFIL",
            joinColumns = @JoinColumn(name = "PROF_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERF_ID")
    )
    private List<Perfil> perfils = new ArrayList<Perfil>();


}
