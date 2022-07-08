package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.dto.response.AplicativoDTO;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorAplicativoRepository implements PanacheRepositoryBase<AplicativoDTO, Long> {
}
