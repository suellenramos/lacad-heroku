package org.barros.modules.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "aplicativo_favorito", schema = "public")
public class AplicativoFavorito implements Serializable {

    //Continuar
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "af_id")
    private Long id;

    @Column(name = "af_favorito")
    private Boolean favorito;

//    @ManyToOne
//    @JoinColumn(name = "prof_id")
//    private   Professor professor;

    @ManyToOne
    @JoinColumn(name = "ca_id")
    private   ConteudoAplicativo conteudoAplicativo;
}
