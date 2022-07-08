package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.AvaliacaoDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.AvaliacaoService;
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
@Tag(name = "avaliacao", description = "Operacoes  de Avaliações")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/avaliacoes")
public class AvaliacaoController {
//
//    private final AvaliacaoService avaliacaoService;
//
//    @GET
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem todos as Avaliacoes",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.ARRAY, implementation = AvaliacaoDTO.class)
//            )
//    )
//    public Response get() {
//        return Response.ok(avaliacaoService.findAll()).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem Avaliação pelo Id",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = AvaliacaoDTO.class)
//            )
//    )
//
//
//    @APIResponse(
//            responseCode = "404",
//            description = "Avaliação não encontrada pelo Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
//        return avaliacaoService.findById(id)
//                .map(avaliacaoDTO -> Response.ok(avaliacaoDTO).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//
//    @POST
//    @APIResponse(
//            responseCode = "201",
//            description = "Criar Avaliações",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = AvaliacaoDTO.class)
//            )
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Avaliação Invalida",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Já existe um Avaliação com esse Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response post(@NotNull @Valid AvaliacaoDTO avaliacaoDTO, @Context UriInfo uriInfo) {
//        avaliacaoService.save(avaliacaoDTO);
//        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(avaliacaoDTO.getAvaId())).build();
//        return Response.created(uri).entity(avaliacaoDTO).build();
//    }
//
//    @PUT
//    @Path("{id}")
//    @APIResponse(
//            responseCode = "204",
//            description = "Avaliação Atualizada",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = AvaliacaoDTO.class)
//            )
//    )
//
//    @APIResponse(
//            responseCode = "400",
//            description = "Não foi encontrado Id para a Avaliação requerida",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "O id não corresponde a Avaliação requerida",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "404",
//            description = "Nenhuma Avaliação encontrado pelo id indicado",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//
//    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid AvaliacaoDTO avaliacaoDTO) {
//        if (!Objects.equals(id, avaliacaoDTO.getAvaId())) {
//            throw new ServiceException("O id não corresponde a Avaliação");
//        }
//        avaliacaoService.update(avaliacaoDTO);
//        return Response.ok(avaliacaoDTO).build();
//       // return Response.status(Response.Status.NO_CONTENT).build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") Long id) {
//        avaliacaoService.excluir(id);
//    }
}



