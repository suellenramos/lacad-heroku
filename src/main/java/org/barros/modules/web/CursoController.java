package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.CursoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.CursoService;
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
@Tag(name = "curso", description = "Operacoes Cursos")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/cursos")
public class CursoController {
//
//    private final CursoService cursoService;
//
//    @GET
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem todos os Cursos",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.ARRAY, implementation = CursoDTO.class)
//            )
//    )
//    public Response get() {
//        return Response.ok(cursoService.findAll()).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem Curso pelo Id",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = CursoDTO.class)
//            )
//    )
//
//
//    @APIResponse(
//            responseCode = "404",
//            description = "Curso não encontrado pelo Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
//        return cursoService.findById(id)
//                .map(cursoDTO -> Response.ok(cursoDTO).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//
//    @POST
//    @APIResponse(
//            responseCode = "201",
//            description = "Criar Cursos",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = CursoDTO.class)
//            )
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Curso Invalido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Já existe um Curso com esse Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response post(@NotNull @Valid CursoDTO cursoDTO, @Context UriInfo uriInfo) {
//        cursoService.saveCurso(cursoDTO);
//        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(cursoDTO.getCurId())).build();
//        return Response.created(uri).entity(cursoDTO).build();
//    }
//
//    @PUT
//    @Path("{id}")
//    @APIResponse(
//            responseCode = "204",
//            description = "Curso Atualizado",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = CursoDTO.class)
//            )
//    )
//
//    @APIResponse(
//            responseCode = "400",
//            description = "Não foi encontrado Id para o Curso requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "O id não corresponde ao Curso requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "404",
//            description = "Nenhum Curso encontrado pelo id indicado",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//
//    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid CursoDTO cursoDTO) {
//        if (!Objects.equals(id, cursoDTO.getCurId())) {
//            throw new ServiceException("O id não corresponde ao Curso");
//        }
//        cursoService.update(cursoDTO);
//        return Response.ok(cursoDTO).build();
//       // return Response.status(Response.Status.NO_CONTENT).build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") Long id) {
//        cursoService.excluir(id);
//    }
}



