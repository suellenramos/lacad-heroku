package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.core.IdDto;
import org.barros.modules.dto.request.ImagemRequestDTO;
import org.barros.modules.dto.response.CursoDTO;
import org.barros.modules.dto.response.ImagemResponseDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.CursoService;
import org.barros.modules.service.IImagemService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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
import java.util.List;
import java.util.Objects;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "curso", description = "Endpoint(s) relacionado(s) a manipulação de Cursos")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Inject
    IImagemService iImagemService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Obtem todos os Cursos",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = CursoDTO.class)
            )
    )
    public Response get() {
        return Response.ok(cursoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            description = "Obtem Curso pelo Id",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = CursoDTO.class)
            )
    )

    @APIResponse(
            responseCode = "404",
            description = "Curso não encontrado pelo Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return cursoService.findById(id)
                .map(cursoDTO -> Response.ok(cursoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Operation(summary = "Salvar Curso", description = "Cria uma novo Curso")
    @APIResponse(responseCode = "201", description = "Curso salvo", content = @Content(schema = @Schema(implementation = CursoDTO.class)))

    @APIResponse(
            responseCode = "400",
            description = "Já existe um Curso com esse Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid CursoDTO cursoDTO, @Context UriInfo uriInfo) {
        cursoService.saveCurso(cursoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(cursoDTO.getCurId())).build();
        return Response.created(uri).entity(cursoDTO).build();
    }

    @POST
    @Path("/{cursoId}/fotos")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA, schema = @Schema(implementation = ImagemRequestDTO.class)))
    @APIResponse(content = {@Content(schema = @Schema(implementation = IdDto.class))}, responseCode = "201")
    //@RolesAllowed({Roles.ESCRITA})
    public Response uploadFoto(@PathParam("cursoId") Long cursoId, @Parameter(hidden = true) MultipartFormDataInput input) {
        return Response.created(null).entity(iImagemService.uploadFoto(cursoId, new ImagemRequestDTO(input))).build();
    }

    @GET
    @Path("/{cursoId}/fotos")
    @Operation(summary = "Buscar Fotos", description = "Busca as fotos do Curso")
   // @RolesAllowed({Roles.ESCRITA, Roles.LEITURA})
    public List<ImagemResponseDTO> buscarFotos(@PathParam("cursoId") Long cursoId) {
        return iImagemService.buscarFotos(cursoId);
    }

    @GET
    @Path("/fotos/{fotoId}")
    @Operation(summary = "Buscar Foto", description = "Busca a foto do Curso de acordo com o Id")
  //  @RolesAllowed({Roles.ESCRITA, Roles.LEITURA})
    public ImagemResponseDTO buscarFoto(@PathParam("fotoId") Long fotoId) {
        return iImagemService.buscarFoto(fotoId);
    }

    @PUT
    @Path("{id}")
    @APIResponse(
            responseCode = "204",
            description = "Curso Atualizado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = CursoDTO.class)
            )
    )

    @APIResponse(
            responseCode = "400",
            description = "Não foi encontrado Id para o Curso requerido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )

    @Operation(summary = "Editar Curso", description = "Edita os dados do Curso")
    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid CursoDTO cursoDTO) {
        if (!Objects.equals(id, cursoDTO.getCurId())) {
            throw new ServiceException("O id não corresponde ao Curso");
        }
        cursoService.update(cursoDTO);
        return Response.ok(cursoDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Pessoa", description = "Exclui os dados do Curso")
    @APIResponse(responseCode = "204", description = "Registro excluído com sucesso")
    public void delete(@PathParam("id") Long id) {
        cursoService.excluir(id);
    }
}



