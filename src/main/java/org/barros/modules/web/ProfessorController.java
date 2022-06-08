package org.barros.modules.web;

import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.service.ProfessorService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(ref = "professor")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@Path("/v1/professores")
public class ProfessorController {

    @Inject
    ProfessorService professorService;

    @Operation(summary = "Cadastra uma novo Professor")
    @PermitAll
    @POST
    public Response salvar(ProfessorDTO professorDTO){
        var professor = professorService.salvar(professorDTO);
        return Response.created(null).entity(professor).build();
    }
}
