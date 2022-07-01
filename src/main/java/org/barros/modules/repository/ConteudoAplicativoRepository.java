package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.ConteudoAplicativo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConteudoAplicativoRepository implements PanacheRepositoryBase<ConteudoAplicativo, Long> {
}
