package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.DisciplinaConteudo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisciplinaConteudoRepository implements PanacheRepositoryBase<DisciplinaConteudo, Long> {
}
