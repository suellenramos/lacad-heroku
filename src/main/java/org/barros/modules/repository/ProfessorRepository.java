package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.barros.modules.model.Professor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepository<Professor> {
}
