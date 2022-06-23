package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.Aplicativo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AplicativoRepository implements PanacheRepositoryBase<Aplicativo, Long> {
}
