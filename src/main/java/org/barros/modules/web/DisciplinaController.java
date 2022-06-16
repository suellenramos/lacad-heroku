package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.DisciplinaService;
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
@Tag(name = "disciplina", description = "Operacoes Disciplinas")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Obtem todas as Disciplinas",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = DisciplinaDTO.class)
            )
    )
    public Response get() {
        return Response.ok(disciplinaService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            description = "Obtem Disciplina pelo Id",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaDTO.class)
            )
    )


    @APIResponse(
            responseCode = "404",
            description = "Disciplina não encontrada pelo Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return disciplinaService.findById(id)
                .map(disciplinaDTO -> Response.ok(disciplinaDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Criar Disciplinas",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaDTO.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Disciplina Inválida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Já existe um Professor com esse Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid DisciplinaDTO disciplinaDTO, @Context UriInfo uriInfo) {
        disciplinaService.save(disciplinaDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(disciplinaDTO.getDiscId())).build();
        return Response.created(uri).entity(disciplinaDTO).build();
    }

    @PUT
    @Path("{id}")
    @APIResponse(
            responseCode = "204",
            description = "Disciplina Atualizada",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaDTO.class)
            )
    )

    @APIResponse(
            responseCode = "400",
            description = "Não foi encontrado Id para a Disciplina requerida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "O id não corresponde a Disciplina requerida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "Nenhuma Disciplina encontrada pelo id indicado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )

    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid DisciplinaDTO disciplinaDTO) {
        if (!Objects.equals(id, disciplinaDTO.getDiscId())) {
            throw new ServiceException("O id não corresponde a Disciplina");
        }
        disciplinaService.update(disciplinaDTO);
        return Response.ok(disciplinaDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        disciplinaService.excluir(id);
    }
}



