package org.barros.modules.security.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.barros.modules.security.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Optional<Usuario> findUsuario(String usuario, String senha) {
        return find("userLogin = ?1 and userPassword = ?2", usuario, senha).firstResultOptional();
    }
}
