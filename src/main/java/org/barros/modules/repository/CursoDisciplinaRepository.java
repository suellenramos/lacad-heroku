package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.CursoDisciplina;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CursoDisciplinaRepository implements PanacheRepositoryBase<CursoDisciplina, Long> {
}
