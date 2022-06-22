package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.Conteudo;
import org.barros.modules.model.Disciplina;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConteudoRepository implements PanacheRepositoryBase<Conteudo, Long> {
}
