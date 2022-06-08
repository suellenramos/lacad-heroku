package org.barros.modules.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "professor", schema = "public")
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "prof_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prof_nome")
    private String nome;

    @Column(name = "prof_email")
    private String email;

    @Column(name = "prof_senha")
    private String senha;
}
