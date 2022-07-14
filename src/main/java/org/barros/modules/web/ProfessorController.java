package org.barros.modules.web;

import io.quarkus.security.Authenticated;
import io.smallrye.common.constraint.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.IProfessorService;
import org.barros.modules.service.ProfessorService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Professor", description = "Endpoint(s) relacionado(s) a manipulação de Professores")
@SecurityRequirement(name = "jwt")
@Path("/v1/professores")
public class ProfessorController {

    @Inject
    IProfessorService iProfessorService;

    @Inject
    ProfessorService professorService;

    @GET
    @Authenticated
    @Operation(summary = "Buscar Professores", description = "Lista todos Professores ")
    public List<ProfessorDTO> buscarTodosProfessores() {
        return iProfessorService.listaProfessores();
    }

    @POST
    @RolesAllowed("professorUPDATE")
    @Operation(summary = "Criar um Professor", description = "Cria um novo Professor ")
    public void inserir(ProfessorDTO professorDTO) {
        iProfessorService.insert(professorDTO);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar um Professor", description = "Cria um novo Professor ")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return professorService.findById(id)
                .map(professorDTO -> Response.ok(professorDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Atualização de um Professor", description = "Cria um novo Professor ")
    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid ProfessorDTO professorDTO) {
        if (!Objects.equals(id, professorDTO.getProfId())) {
            throw new ServiceException("O id não corresponde ao Professor");
        }
        professorService.update(professorDTO);
        return Response.ok(professorDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }
    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remover Professor", description = "Remove um novo Professor ")
    public void delete(@PathParam("id") Long id) {
        professorService.excluir(id);
    }
}



