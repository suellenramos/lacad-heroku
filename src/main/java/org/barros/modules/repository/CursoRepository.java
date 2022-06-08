package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.barros.modules.model.Curso;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CursoRepository implements PanacheRepository<Curso> {
}
