package org.barros.modules.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil", schema = "public")
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perf_id", nullable = false)
    private Long id;

    @Column(name = "perf_nome")
    private String nome;

    @Column(name = "perf_descricao")
    private String descricao;

    @Column(name = "perf_ativo")
    private Boolean ativo;

    @JsonIgnore
    @ManyToMany(mappedBy = "perfils", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Professor> professores = new ArrayList<>();

    @JsonIgnoreProperties({"perfils"})
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERFIL_REGRAS",
            joinColumns = @JoinColumn(name = "PERF_ID"),
            inverseJoinColumns = @JoinColumn(name = "REG_ID")
    )
    private List<Regra> regras = new ArrayList<>();
}
