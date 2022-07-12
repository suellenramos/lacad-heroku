package org.barros.modules.security.service;


import org.barros.modules.security.model.AuthRequest;

import javax.ws.rs.core.Response;

public interface IAuthenticationService {

    Response authentication(AuthRequest authRequest);
}
