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

    private final ConteudoAplicativoService conteudoAplicativoService;

    @GET
    @APIResponse(
            responseCode = "200",
            description = "Obtem todos os Conteudos e Aplicativos",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ConteudoAplicativoDTO.class)
            )
    )
    public Response get() {
        return Response.ok(conteudoAplicativoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(
            responseCode = "200",
            description = "Obtem Conteúdo e APlicativo pelo Id",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ConteudoAplicativoDTO.class)
            )
    )

    @APIResponse(
            responseCode = "404",
            description = "Conteúdo e Aplicativo não encontrado pelo Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return conteudoAplicativoService.findById(id)
                .map(conteudoAplicativoDTO -> Response.ok(conteudoAplicativoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @POST
    @APIResponse(
            responseCode = "201",
            description = "Criação de vínculo entre Conteúdo e Aplicativo",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ConteudoAplicativoDTO.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Conteúdo e Aplicativo Inválido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Já existe um Conteúdo e Aplicativo com esse Id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response post(@NotNull @Valid ConteudoAplicativoDTO conteudoAplicativoDTO, @Context UriInfo uriInfo) {
        conteudoAplicativoService.save(conteudoAplicativoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(conteudoAplicativoDTO.getId())).build();
        return Response.created(uri).entity(conteudoAplicativoDTO).build();
    }
    @PUT
    @Path("{id}")
    @APIResponse(
            responseCode = "204",
            description = "Conteúdo e Aplicativo Atualizado",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = ConteudoAplicativoDTO.class)
            )
    )

    @APIResponse(
            responseCode = "400",
            description = "Não foi encontrado Id para o Conteúdo e Aplicativo requerido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "O id não corresponde ao Conteúdo e ao Aplicativo requerido",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "Nenhum Conteúdo e Aplicativo encontrado pelo id indicado",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )

    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid ConteudoAplicativoDTO conteudoAplicativoDTO) {
        if (!Objects.equals(id, conteudoAplicativoDTO.getId())) {
            throw new ServiceException("O id não corresponde ao Conteúdo e Aplicativo");
        }
        conteudoAplicativoService.update(conteudoAplicativoDTO);
        return Response.ok(conteudoAplicativoDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        conteudoAplicativoService.excluir(id);
    }
}



