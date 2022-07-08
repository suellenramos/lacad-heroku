package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ConteudoAplicativoDTO;
import org.barros.modules.dto.response.CursoDisciplinaDTO;
import org.barros.modules.dto.response.DisciplinaConteudoDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.ConteudoAplicativoService;
import org.barros.modules.service.CursoDisciplinaService;
import org.barros.modules.service.DisciplinaConteudoService;
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
@Tag(name = "disciplinaConteudo", description = "Operacoes de Disciplinas e Conteúdo")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/disciplinasConteudos")
public class DisciplinaConteudoController {
//
//    private final DisciplinaConteudoService disciplinaConteudoService;
//
//    @GET
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem todos as Disciplinas e Conteúdos",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.ARRAY, implementation = DisciplinaConteudoDTO.class)
//            )
//    )
//    public Response get() {
//        return Response.ok(disciplinaConteudoService.findAll()).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem Disciplina e Conteúdos pelo Id",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaConteudoDTO.class)
//            )
//    )
//
//    @APIResponse(
//            responseCode = "404",
//            description = "Disciplina e Conteúdo não encontrado pelo Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
//        return disciplinaConteudoService.findById(id)
//                .map(disciplinaConteudoDTO -> Response.ok(disciplinaConteudoDTO).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//    @POST
//    @APIResponse(
//            responseCode = "201",
//            description = "Criação de vínculo entre Disciplina e Conteúdo",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaConteudoDTO.class)
//            )
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Disciplina e Conteúdo Inválido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Já existe uma Disciplima e Conteúdo com esse Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response post(@NotNull @Valid DisciplinaConteudoDTO disciplinaConteudoDTO, @Context UriInfo uriInfo) {
//        disciplinaConteudoService.save(disciplinaConteudoDTO);
//        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(disciplinaConteudoDTO.getId())).build();
//        return Response.created(uri).entity(disciplinaConteudoDTO).build();
//    }
//    @PUT
//    @Path("{id}")
//    @APIResponse(
//            responseCode = "204",
//            description = " Disciplina e Conteúdo Atualizado",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaConteudoDTO.class)
//            )
//    )
//
//    @APIResponse(
//            responseCode = "400",
//            description = "Não foi encontrado Id para a Disciplina e ao Conteúdo requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "O id não corresponde a Disciplina e ao Conteúdo requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "404",
//            description = "Nenhuma Disciplina e Conteúdo encontrado pelo id indicado",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//
//    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid DisciplinaConteudoDTO disciplinaConteudoDTO) {
//        if (!Objects.equals(id, disciplinaConteudoDTO.getId())) {
//            throw new ServiceException("O id não corresponde a Disciplina e ao Conteúdo");
//        }
//        disciplinaConteudoService.update(disciplinaConteudoDTO);
//        return Response.ok(disciplinaConteudoDTO).build();
//       // return Response.status(Response.Status.NO_CONTENT).build();
//    }
//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") Long id) {
//        disciplinaConteudoService.excluir(id);
//    }
}



