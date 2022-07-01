package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.ProfessorAplicativo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorAplicativoRepository implements PanacheRepositoryBase<ProfessorAplicativo, Long> {
}
