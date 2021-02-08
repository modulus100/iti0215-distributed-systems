package taltech.app.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import taltech.app.controller.requests.CreateUserRequest;
import taltech.app.services.UserService;
import taltech.core.models.User;

@Path("user")
@RequestScoped
public class UserController {

    @Inject
    private UserService userService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        return Response
                .ok(new User(id, "TaraDestroyer"))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response
                .ok(userService.getUsers())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserRequest request) {
        return Response
                .ok(new User(1, request.getName()))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        return Response
                .ok(new User(id, "TaraDestroyer"))
                .build();
    }
}
