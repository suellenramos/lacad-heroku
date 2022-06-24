package org.barros.modules.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.Aplicativo;
import org.barros.modules.model.Avaliacao;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoRepository implements PanacheRepositoryBase<Avaliacao, Long> {
}
