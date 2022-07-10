package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.dto.response.AplicativoDTO;
import org.barros.modules.model.AplicativoFavorito;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AplicativoFavoritoRepository implements PanacheRepositoryBase<AplicativoFavorito, Long> {
}
