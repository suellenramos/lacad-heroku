package org.barros.modules.web;

import io.quarkus.security.Authenticated;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.core.IdDto;
import org.barros.modules.dto.request.ImagemRequestDTO;
import org.barros.modules.dto.response.AplicativoDTO;
import org.barros.modules.dto.response.ImagemResponseDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.AplicativoService;
import org.barros.modules.service.IImagemService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
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
@Tag(name = "Aplicativo", description = "Endpoint(s) relacionado(s) a manipulação de Aplicativos")
@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/aplicativos")
@Authenticated
public class AplicativoController {

    private final AplicativoService aplicativoService;

    @Inject
    IImagemService iImagemService;

    @GET
    @APIResponse(responseCode = "200", description = "Obtem todos os Aplicativos", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.ARRAY, implementation = AplicativoDTO.class)))
    @Operation(summary = "Buscar Aplicativos", description = "Obtem todos os Aplicativos")
    public Response get() {
        return Response.ok(aplicativoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Obtem Aplicativo pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = AplicativoDTO.class)))
    @APIResponse(responseCode = "404", description = "Aplicativo não encontrado pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Buscar Aplicativo por Id", description = "Busca Aplicativo por Id")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return aplicativoService.findById(id)
                .map(aplicativoDTO -> Response.ok(aplicativoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Operation(summary = "Salvar Aplicativo", description = "Cria uma novo Aplicativo")
    @APIResponse(responseCode = "201", description = "Aplicativo salvo", content = @Content(schema = @Schema(implementation = AplicativoDTO.class)))
    @APIResponse(responseCode = "400", description = "Já existe um Aplicativo com esse Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response post(@NotNull @Valid AplicativoDTO aplicativoDTO, @Context UriInfo uriInfo) {
        aplicativoService.save(aplicativoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(aplicativoDTO.getApliId())).build();
        return Response.created(uri).entity(aplicativoDTO).build();
    }

    @POST
    @Path("/{aplicativoId}/fotos")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = ImagemRequestDTO.class)))
    @APIResponse(content = {@Content(schema = @Schema(implementation = IdDto.class))}, responseCode = "201")
    public Response uploadFoto(@PathParam("aplicativoId") Long aplicativoId, @Parameter(hidden = true) MultipartFormDataInput input) {
        return Response.created(null).entity(iImagemService.uploadFoto(aplicativoId, new ImagemRequestDTO(input))).build();
    }

    @GET
    @Path("/{aplicativoId}/fotos")
    @Operation(summary = "Buscar Fotos", description = "Busca as fotos dos Aplicativos")
    public ImagemResponseDTO buscarFotos(@PathParam("aplicativoId") Long aplicativoId) {
        return iImagemService.buscarFotoByAplicativo(aplicativoId);
    }

    @GET
    @Path("/fotos/{fotoId}")
    @Operation(summary = "Buscar Foto", description = "Busca a foto de Aplicativo de acordo com o Id")
    public ImagemResponseDTO buscarFoto(@PathParam("fotoId") Long fotoId) {
        return iImagemService.buscarFoto(fotoId);
    }


    @PUT
    @Path("{id}")
    @APIResponse(responseCode = "204", description = "Aplicativo Atualizado", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = AplicativoDTO.class)))
    @APIResponse(responseCode = "400", description = "Não foi encontrado Id para o Aplicativo requerido", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Atualizar Aplicativo", description = "Atualiza os dados do Aplicativo")
    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid AplicativoDTO aplicativoDTO) {
        if (!Objects.equals(id, aplicativoDTO.getApliId())) {
            throw new ServiceException("O id correspondente também deverá ser passado no parâmetro");
        }
        aplicativoService.update(aplicativoDTO);
        return Response.ok(aplicativoDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Aplicativo", description = "Exclui os dados do Aplicativo")
    @APIResponse(responseCode = "204", description = "Registro excluído com sucesso")
    //@RolesAllowed({"professorSELECT", "professorUPDATE"})
    public void delete(@PathParam("id") Long id) {
        aplicativoService.excluir(id);
    }
}



