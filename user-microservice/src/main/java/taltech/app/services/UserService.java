package taltech.app.services;

import taltech.app.controller.requests.CreateUserRequest;
import taltech.app.controller.responses.CreateUserResponse;
import taltech.app.controller.responses.GetUserResponse;
import taltech.app.controller.responses.GetUsersResponse;
import taltech.core.models.User;
import taltech.core.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    private UserRepository repository;

    @PostConstruct
    public void initRepos() {
        repository = new UserRepository();
    }

    public GetUsersResponse getAll() {
        return new GetUsersResponse(repository.getAll());
    }

    public GetUserResponse getById(int id) {
        return new GetUserResponse(repository.getById(id));
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        return new CreateUserResponse(repository.create(user)) ;
    }
}