package org.barros.modules.security.service;


import lombok.SneakyThrows;
import org.barros.modules.security.dto.AuthResponseDto;
import org.barros.modules.security.repository.UsuarioRepository;
import org.barros.modules.security.utils.TokenManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    UsuarioRepository repository;

    @Inject
    TokenManager tokenManager;

    @SneakyThrows
    public AuthResponseDto login(String login, String password) {
        var user = repository.findUsuario(login, password).orElseThrow(() -> new NotFoundException("Usuário ou senha inválida"));
        return tokenManager.generateToken(user);
    }

    public AuthResponseDto refreshToken(Long  userId) {
        return tokenManager.generateToken(repository.findById(userId));
    }
}
