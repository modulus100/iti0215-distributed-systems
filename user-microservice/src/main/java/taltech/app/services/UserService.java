package taltech.app.services;

import lombok.SneakyThrows;
import taltech.app.controller.responses.GetUsersResponse;
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

    @SneakyThrows
    public GetUsersResponse getUsers() {
        return new GetUsersResponse(repository.getUsers());
    }
}