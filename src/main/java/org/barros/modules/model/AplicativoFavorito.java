package org.barros.modules.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "aplicativo_favorito", schema = "public")
public class AplicativoFavorito implements Serializable {

    //Continuar
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ap_id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "prof_id")
//    private   Professor professor;
//    @ManyToOne
//    @JoinColumn(name = "apli_id")
//    private   Aplicativo aplicativo;
}
