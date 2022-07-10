package org.barros.modules.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "conteudo_aplicativo", schema = "public")
public class ConteudoAplicativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ca_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dc_id")
    private   DisciplinaConteudo disciplinaConteudo;

    @ManyToOne
    @JoinColumn(name = "apli_id")
    private   Aplicativo aplicativo;

    @OneToMany(mappedBy = "conteudoAplicativo", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AplicativoFavorito> aplicativoFavoritos ;
}
