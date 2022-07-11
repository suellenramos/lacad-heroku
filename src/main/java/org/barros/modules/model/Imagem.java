package org.barros.modules.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "imagem", schema = "public")
public class Imagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "im_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imId;

    @OneToOne
    @JoinColumn(name = "apli_id")
    private Aplicativo aplicativo;

    @Column(name = "im_data")
    private LocalDate imData;

    @Column(name = "im_bucket", length = 50)
    private String imBucket;

    @Column(name = "im_hash", length = 50)
    private String imHash;

    @PrePersist
    void prePersist() {
        imData = LocalDate.now();
    }
}
