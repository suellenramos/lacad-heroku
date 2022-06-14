package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.ProfessorService;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Objects;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "professor", description = "Operacoes Professores")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Obtem todos os Professores",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ProfessorDTO.class)
            )
    )
    public Response get() {
        return Response.ok(professorService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            description = "Obtem Professor pelo Id",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ProfessorDTO.class)
            )
    )


    @APIResponse(
            responseCode = "404",
            description = "Professor não encontrado pelo Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return professorService.findById(id)
                .map(professorDTO -> Response.ok(professorDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Criar Professores",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ProfessorDTO.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Professor Invalido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Já existe um Professor com esse Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid ProfessorDTO professorDTO, @Context UriInfo uriInfo) {
        professorService.save(professorDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(professorDTO.getProfId())).build();
        return Response.created(uri).entity(professorDTO).build();
    }

    @PUT
    @Path("{id}")
    @APIResponse(
            responseCode = "204",
            description = "Professor Atualizado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ProfessorDTO.class)
            )
    )

    @APIResponse(
            responseCode = "400",
            description = "Não foi encontrado Id para o Professor requerido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "O id não corresponde ao Professor requerido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "Nenhum professor encontrado pelo id indicado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )

    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid ProfessorDTO professorDTO) {
        if (!Objects.equals(id, professorDTO.getProfId())) {
            throw new ServiceException("O id não corresponde ao Professor.ProfessorDTO.profId");
        }
        professorService.update(professorDTO);
        return Response.ok(professorDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        professorService.excluir(id);
    }
}



