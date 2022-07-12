package org.barros.modules.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.barros.modules.core.enums.TipoRegra;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "regras", schema = "public")
public class Regra extends PanacheEntityBase {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reg_id", nullable = false)
    private Long id;

    @Column(name = "reg_nome")
    private String nome;

    @Column(name = "reg_descricao")
    private String descricao;

    @Column(name = "reg_tipo")
    @Enumerated(EnumType.STRING)
    private TipoRegra tipoRegra;

    @JsonIgnore
    @ManyToMany(mappedBy = "regras", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Perfil> perfils = new ArrayList<>();

    public String regraString(){
        if(nome != null && tipoRegra != null) {
            return nome + tipoRegra.getName();
        }
        return null;
    }

    public void setNome(String nome) {
        this.nome = nome.toLowerCase();
    }
}
