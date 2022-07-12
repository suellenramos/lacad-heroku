package org.barros.modules.security.service;


import org.barros.modules.model.Perfil;
import org.barros.modules.model.Professor;
import org.barros.modules.model.Regra;
import org.barros.modules.security.model.AuthRequest;
import org.barros.modules.security.model.AuthResponse;
import org.barros.modules.service.IProfessorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class AuthenticationService implements IAuthenticationService {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @Inject
    IProfessorService iProfessorService;

    @Inject
    TokenUtils tokenUtils;

    @Override
    public Response authentication(AuthRequest authRequest) {
        Professor professor = iProfessorService.findByNomeAndSenha(authRequest.getUsername(), passwordEncoder.encode(authRequest.getPassword()));

        if (professor != null) {
            try {
                Set<Regra> roles = new HashSet<>();
                if(professor.getPerfils() != null && !professor.getPerfils().isEmpty()) {
                    for (Perfil perfil : professor.getPerfils()) {
                        if(perfil.getRegras() != null && !perfil.getRegras().isEmpty()) {
                            for (Regra regra : perfil.getRegras()) {
                                roles.add(regra);
                            }
                        }
                    }
                }
                return Response.ok(new AuthResponse(tokenUtils.generateToken(professor.getNome(), roles))).build();
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
