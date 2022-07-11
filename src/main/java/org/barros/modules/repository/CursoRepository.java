package org.barros.modules.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.Curso;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CursoRepository implements PanacheRepositoryBase<Curso, Long> {
}
