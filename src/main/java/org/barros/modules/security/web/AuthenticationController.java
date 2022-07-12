package org.barros.modules.security.web;


import org.barros.modules.security.model.AuthRequest;
import org.barros.modules.security.service.IAuthenticationService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationController {

	@Inject
    IAuthenticationService iAuthenticationService;

	@PermitAll
	@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
	public Response login(@Valid AuthRequest authRequest) {
		return iAuthenticationService.authentication(authRequest);
	}

}
