package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ConteudoAplicativoDTO;
import org.barros.modules.dto.response.FavoritoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.ConteudoAplicativoService;
import org.barros.modules.service.FavoritoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
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
@Tag(name = "Favorito", description = "Endpoint(s) relacionado(s) a manipulação de Aplicativos favoritos do Professor")
@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    @GET
    @APIResponse(responseCode = "200", description = "Obtem todos os Aplicativos Favoritos do Professores", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.ARRAY, implementation = FavoritoDTO.class)))
    @Operation(summary = "Buscar todos Aplicativos Favoritos do Professor", description = "Obtem todos Aplicativos Favoritos do Professor")
    public Response get() {
        return Response.ok(favoritoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Obtem Aplicativo Favorito pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = FavoritoDTO.class)))
    @APIResponse(responseCode = "404", description = "Aplicativo Favorito não encontrado pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Buscar Aplicativo Favorito pelo Id", description = "Busca Aplicativo Favorito pelo Id")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return favoritoService.findById(id)
                .map(favoritoDTO -> Response.ok(favoritoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @POST
    @Operation(summary = "Salvar Aplicativo Favorito", description = "Cria uma novo Aplicativo Favorito")
    @APIResponse(responseCode = "201", description = "Aplicativo Favorito salvo", content = @Content(schema = @Schema(implementation = FavoritoDTO.class)))
    @APIResponse(responseCode = "400", description = "Já existe um Aplicativo Favorito com esse Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response post(@NotNull @Valid FavoritoDTO favoritoDTO, @Context UriInfo uriInfo) {
        favoritoService.save(favoritoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(favoritoDTO.getFavoId())).build();
        return Response.created(uri).entity(favoritoDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Aplicativo Favorito", description = "Exclui os dados de Aplicativo Favorito")
    @APIResponse(responseCode = "204", description = "Registro excluído com sucesso")
    public void delete(@PathParam("id") Long id) {
        favoritoService.excluir(id);
    }
}



