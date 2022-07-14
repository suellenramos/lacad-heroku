package org.barros.modules.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.barros.modules.model.Professor;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Long> {

    public Professor findByLoginSenha(String email, String senha){
        return find("UPPER(email) like UPPER(?1) AND UPPER(password) like UPPER(?2) AND ativo = true", email, senha).firstResult();
    }
}
