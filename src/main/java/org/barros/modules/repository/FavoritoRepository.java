package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.ConteudoAplicativo;
import org.barros.modules.model.Favorito;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FavoritoRepository implements PanacheRepositoryBase<Favorito, Long> {
}
