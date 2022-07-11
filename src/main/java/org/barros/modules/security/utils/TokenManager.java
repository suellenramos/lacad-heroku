package org.barros.modules.security.utils;

import io.smallrye.jwt.build.Jwt;
import org.barros.modules.security.dto.AuthResponseDto;
import org.barros.modules.security.model.Role;
import org.barros.modules.security.model.Usuario;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;

@ApplicationScoped
public class TokenManager {

    @ConfigProperty(name = "security.token.expiration")
    Long acessTokenExpiration;
    @ConfigProperty(name = "security.refreshtoken.expiration")
    Long refreshTokenExpiration;

    public AuthResponseDto generateToken(Usuario user) {
        var auth = AuthResponseDto.builder();
        long currentTimeInSecs = currentTimeInSecs();

        var claimsBuilder = Jwt.issuer("http://www.lacad.com.br/")
                .issuedAt(currentTimeInSecs)
                .expiresAt(currentTimeInSecs + refreshTokenExpiration)
                .claim("userId", user.getId())
                .subject(user.getUserLogin())
                .claim("type", "Refresh");

        auth.refresh_token(claimsBuilder.sign());
        auth.refresh_expires_in(refreshTokenExpiration);

        claimsBuilder.groups(user.getRoles().stream().map(Role::getNome).collect(Collectors.toUnmodifiableSet()))
                .expiresAt(currentTimeInSecs + acessTokenExpiration)
                .claim("type", "Bearer");

        auth.access_token(claimsBuilder.sign());
        auth.expires_in(acessTokenExpiration);
        return auth.build();
    }

    private int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}