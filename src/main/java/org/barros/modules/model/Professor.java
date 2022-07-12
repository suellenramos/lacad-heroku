package org.barros.modules.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "professor", schema = "public")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prof_id", nullable = false)
    private Long profId;

    @Column(name = "prof_nome")
    private String nome;

    @Column(name = "prof_email")
    private String email;

    @Column(name = "prof_password")
    private String password;

    @CreationTimestamp
    @Column(name = "prof_data_hora_cadastro")
    private LocalDateTime dataInsercao = LocalDateTime.now();

    @Column(name = "prof_ativo")
    private Boolean ativo = true;

    @OneToMany(mappedBy = "professor")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "professor")
    private List<CursoDisciplina> cursoDisciplinas;

    @JsonIgnoreProperties({"pessoas"})
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PESSOA_PERFIL",
            joinColumns = @JoinColumn(name = "PES_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERF_ID")
    )
    private List<Perfil> perfils = new ArrayList<Perfil>();


}
