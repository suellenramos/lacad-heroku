package org.barros.modules.core;


import org.eclipse.microprofile.openapi.OASFilter;


public class OpenApiConfigurator implements OASFilter {
//    @Override
//    public Operation filterOperation(Operation operation) {
//        operation.getResponses().addAPIResponse("500", serverErrorResponse());
//        return OASFilter.super.filterOperation(operation);
//    }
//
//
//    @Override
//    public PathItem filterPathItem(PathItem pathItem) {
//        if (pathItem.getGET() != null)
//            pathItem.getGET().getResponses().addAPIResponse("404", notFoundResponse());
//        if (pathItem.getPUT() != null) {
//            var resp = pathItem.getPUT().getResponses();
//            resp.addAPIResponse("404", notFoundResponse());
//        }
//        if (pathItem.getDELETE() != null) {
//            var resp = pathItem.getPUT().getResponses();
//            resp.addAPIResponse("404", notFoundResponse());
//        }
//        return OASFilter.super.filterPathItem(pathItem);
//    }
//
//    APIResponseImpl notFoundResponse() {
//        var content = new ContentImpl();
//        var resp = new APIResponseImpl();
//        var media = new MediaTypeImpl();
//        var schema = new SchemaImpl();
//        resp.setDescription("Registro não encontrado");
//        resp.setResponseCode("404");
//        schema.type(Schema.SchemaType.STRING);
//        schema.setRef("#/components/schemas/ErrorResponseBody");
//
//        media.setSchema(schema);
//        content.addMediaType("application/json", media);
//
//        resp.setContent(content);
//        return resp;
//    }
//
//    APIResponseImpl serverErrorResponse() {
//        var content = new ContentImpl();
//        var resp = new APIResponseImpl();
//        var media = new MediaTypeImpl();
//        var schema = new SchemaImpl();
//        resp.setDescription("Ocorreu um erro inexperado");
//        resp.setResponseCode("500");
//        schema.type(Schema.SchemaType.STRING);
//        schema.setRef("#/components/schemas/ErrorResponseBody");
//
//        media.setSchema(schema);
//        content.addMediaType("application/json", media);
//
//        resp.setContent(content);
//        return resp;
//    }
//
//    @Override
//    public Schema filterSchema(Schema schema) {
//
//        return OASFilter.super.filterSchema(schema);
//    }

}
