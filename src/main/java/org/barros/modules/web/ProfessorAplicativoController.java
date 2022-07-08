package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.DisciplinaConteudoDTO;
import org.barros.modules.dto.response.ProfessorAplicativoDTO;
import org.barros.modules.dto.response.ProfessorDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.DisciplinaConteudoService;
import org.barros.modules.service.ProfessorAplicativoService;
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
@Tag(name = "professorAplicativo", description = " Operações de Professores e Aplicativos")
//@RolesAllowed("LacadAdmin")
//@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/professorAplicativos")
public class ProfessorAplicativoController {
//
//    private final ProfessorAplicativoService professorAplicativoService;
//
//    @GET
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem todos os Professores e Aplicativos ",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.ARRAY, implementation = ProfessorAplicativoDTO.class)
//            )
//    )
//    public Response get() {
//        return Response.ok(professorAplicativoService.findAll()).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @APIResponse(
//            responseCode = "200",
//            description = "Obtem Professores e Aplicativo pelo Id",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = ProfessorAplicativoDTO.class)
//            )
//    )
//
//    @APIResponse(
//            responseCode = "404",
//            description = "Professor e Conteúdo não encontrado pelo Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
//        return professorAplicativoService.findById(id)
//                .map(professorAplicativoDTO -> Response.ok(professorAplicativoDTO).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
//    @POST
//    @APIResponse(
//            responseCode = "201",
//            description = "Criação de vínculo entre Professor e Aplicativo",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = ProfessorAplicativoDTO.class)
//            )
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Professor e Aplicativo Inválido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "Já existe uma Professor e um Aplicativo com esse Id",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    public Response post(@NotNull @Valid ProfessorAplicativoDTO professorAplicativoDTO, @Context UriInfo uriInfo) {
//        professorAplicativoService.save(professorAplicativoDTO);
//        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(professorAplicativoDTO.getId())).build();
//        return Response.created(uri).entity(professorAplicativoDTO).build();
//    }
//    @PUT
//    @Path("{id}")
//    @APIResponse(
//            responseCode = "204",
//            description = "Professor e Aplicativo Atualizado",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON,
//                    schema = @Schema(type = SchemaType.OBJECT, implementation = ProfessorAplicativoDTO.class)
//            )
//    )
//
//    @APIResponse(
//            responseCode = "400",
//            description = "Não foi encontrado Id para o Professor e aPLICATIVO requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "400",
//            description = "O id não corresponde ao Professor e ao Aplicativo requerido",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//    @APIResponse(
//            responseCode = "404",
//            description = "Nenhum Professor e Aplicativo encontrado pelo id indicado",
//            content = @Content(mediaType = MediaType.APPLICATION_JSON)
//    )
//
//    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid ProfessorAplicativoDTO professorAplicativoDTO) {
//        if (!Objects.equals(id, professorAplicativoDTO.getId())) {
//            throw new ServiceException("O id não corresponde ao Professor e Aplicativo");
//        }
//        professorAplicativoService.update(professorAplicativoDTO);
//        return Response.ok(professorAplicativoDTO).build();
//       // return Response.status(Response.Status.NO_CONTENT).build();
//    }
//    @DELETE
//    @Path("/{id}")
//    public void delete(@PathParam("id") Long id) {
//        professorAplicativoService.excluir(id);
//    }
}



