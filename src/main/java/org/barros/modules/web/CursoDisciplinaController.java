package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.CursoDisciplinaDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.CursoDisciplinaService;
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
@Tag(name = "cursoDisciplina", description = "Operacoes de Cursos e Disciplinas")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/cursosDisciplinas")
public class CursoDisciplinaController {

    private final CursoDisciplinaService cursoDisciplinaService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Obtem todos os Cursos Disciplinas",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = CursoDisciplinaDTO.class)
            )
    )
    public Response get() {
        return Response.ok(cursoDisciplinaService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            description = "Obtem Curso e Disciplinas pelo Id",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = CursoDisciplinaDTO.class)
            )
    )

    @APIResponse(
            responseCode = "404",
            description = "Curso e Disciplina não encontrada pelo Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return cursoDisciplinaService.findById(id)
                .map(cursoDisciplinaDTO -> Response.ok(cursoDisciplinaDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @POST
    @APIResponse(
            responseCode = "201",
            description = "Criação de vínculo entre Curso e Disciplina",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = CursoDisciplinaDTO.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Curso e Disciplina Inválida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Já existe um Curso e Disciplina com esse Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid CursoDisciplinaDTO cursoDisciplinaDTO, @Context UriInfo uriInfo) {
        cursoDisciplinaService.save(cursoDisciplinaDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(cursoDisciplinaDTO.getId())).build();
        return Response.created(uri).entity(cursoDisciplinaDTO).build();
    }
    @PUT
    @Path("{id}")
    @APIResponse(
            responseCode = "204",
            description = "Curso e Disciplina Atualizada",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ProfessorDTO.class)
            )
    )

    @APIResponse(
            responseCode = "400",
            description = "Não foi encontrado Id para o Curso e a Disciplina requerida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "O id não corresponde ao Curso e a Disciplina requerida",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "Nenhum Curso e Disciplina encontrado pelo id indicado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )

    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid CursoDisciplinaDTO cursoDisciplinaDTO) {
        if (!Objects.equals(id, cursoDisciplinaDTO.getId())) {
            throw new ServiceException("O id não corresponde ao Curso e a Disciplina");
        }
        cursoDisciplinaService.update(cursoDisciplinaDTO);
        return Response.ok(cursoDisciplinaDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        cursoDisciplinaService.excluir(id);
    }
}



