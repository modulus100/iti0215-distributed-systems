package taltech.app.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import taltech.app.requests.CreateItemRequest;
import taltech.app.services.ItemService;

@Path("item")
@RequestScoped
public class InventoryController {

    @Inject
    private ItemService itemService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") int id) {
        return Response
                .ok(itemService.getById(id))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        return Response
                .ok(itemService.getAll())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(CreateItemRequest request) {
        return Response
                .ok(itemService.create(request))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") int id) {
        itemService.deleteById(id);
        return Response
                .ok()
                .build();
    }
}

