package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.AplicativoFavoritoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.AplicativoFavoritoService;
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
@Tag(name = "aplicativoFavorito", description = " Operações de Aplicativo Favorito")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/aplicativoFavoritos")
public class AplicativoFavoritoController {

    private final AplicativoFavoritoService aplicativoFavoritoService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Obtem todos os Aplicativos Favoritos ",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = AplicativoFavoritoDTO.class)
            )
    )
    public Response get() {
        return Response.ok(aplicativoFavoritoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            description = "Obtem Aplicativo Favorito pelo Id",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = AplicativoFavoritoDTO.class)
            )
    )

    @APIResponse(
            responseCode = "404",
            description = "Aplicativo Favorito não encontrado pelo Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return aplicativoFavoritoService.findById(id)
                .map(aplicativoFavoritoDTO -> Response.ok(aplicativoFavoritoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @POST
    @APIResponse(
            responseCode = "201",
            description = "Criação de Aplicativo Favorito",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = AplicativoFavoritoDTO.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Aplicativo Favorito Inválido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Já existe um Aplicativo Favorito com esse Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid AplicativoFavoritoDTO aplicativoFavoritoDTO, @Context UriInfo uriInfo) {
        aplicativoFavoritoService.save(aplicativoFavoritoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(aplicativoFavoritoDTO.getId())).build();
        return Response.created(uri).entity(aplicativoFavoritoDTO).build();
    }
//    @PUT
//    @Path("{id}")
//    @APIResponse(
//            responseCode = "204",
//            description = "Aplicativo Favorito Atualizado",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = AplicativoFavoritoDTO.class)
//            )
//    )
//
//    @APIResponse(
//            responseCode = "400",
//            description = "Não foi encontrado Id para o Aplicativo Favorito requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "O id não corresponde ao  Aplicativo Favorito requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "404",
//            description = "Nenhum Aplicativo Favorito  encontrado pelo id indicado",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//
//    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid AplicativoFavoritoDTO aplicativoFavoritoDTO) {
//        if (!Objects.equals(id, aplicativoFavoritoDTO.getId())) {
//            throw new ServiceException("O id não corresponde ao Aplicativo Favorito");
//        }
//        aplicativoFavoritoService.update(aplicativoFavoritoDTO);
//        return Response.ok(aplicativoFavoritoDTO).build();
//       // return Response.status(Response.Status.NO_CONTENT).build();
//    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        aplicativoFavoritoService.excluir(id);
    }
}



