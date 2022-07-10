package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.barros.modules.model.Imagem;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImagemRepository implements PanacheRepository<Imagem> {
}
