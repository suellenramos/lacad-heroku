package org.barros.modules.multipart;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Path("/client")
public class MultipartClientResource {

    @Inject
    @RestClient
    MultipartService service;

    @PermitAll
    @POST
    @Path("/multipart")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendFile() throws Exception {
        MultipartBody body = new MultipartBody();
        body.fileName = "greeting.txt";
        body.file = new ByteArrayInputStream("HELLO WORLD".getBytes(StandardCharsets.UTF_8));
        return service.sendMultipartData(body);
    }
}