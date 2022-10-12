package org.altice.controller;

import org.altice.exception.AlticciInvalidIndexException;
import org.altice.service.AlticciServiceInterface;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/alticci")
@Produces(MediaType.APPLICATION_JSON)
public class AlticciController {

    private final AlticciServiceInterface alticciService;

    @Inject
    public AlticciController(AlticciServiceInterface alticciService) {
        this.alticciService = alticciService;
    }


    @GET
    @Path("/{n}")
    public Response getIndexAlticciValue(@PathParam("n") final int n) throws AlticciInvalidIndexException {

        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(alticciService.getOnlyIndexAlticciSequenceValues(n))
                    .build();
        } catch (AlticciInvalidIndexException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage())
                    .build();

        }

    }

    @GET
    @Path("/{n}/full_sequence")
    public Response getFullSequenceIndexAlticciValue(@PathParam("n") final int n) throws AlticciInvalidIndexException {

        try {
            return Response.ok(alticciService.getAllIndexAlticciSequenceValues(n)).build();
        } catch (AlticciInvalidIndexException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(e.getMessage())
                    .build();

        }

    }
}
