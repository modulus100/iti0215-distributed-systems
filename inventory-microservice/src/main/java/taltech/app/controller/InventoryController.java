package taltech.app.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import taltech.app.requests.CreateItemRequest;
import taltech.core.models.Item;

@Path("inventory/item")
@ApplicationScoped
public class InventoryController {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") int id) {
        return Response
                .ok(new Item(1, 1, "iphone", "just an item"))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        return Response
                .ok(new Item(1, 1, "iphone", "just an item"))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(CreateItemRequest request) {
        return Response
                .ok(new Item(1, 1, request.getName(), request.getDescription()))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") int id) {
        return Response
                .ok(new Item(1, 1, "iphone", "just an item"))
                .build();
    }
}

