package org.barros.modules.security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "role", schema = "public")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "role_nome", nullable = false, length = 40)
    private String nome;

    @ManyToMany
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Usuario> usuarios = new LinkedHashSet<>();
}