package taltech.app.services;

import taltech.app.controller.requests.CreateItemRequest;
import taltech.app.controller.responses.CreateItemResponse;
import taltech.app.controller.responses.GetItemResponse;
import taltech.app.controller.responses.GetItemsResponse;
import taltech.core.models.Item;
import taltech.core.repositories.ItemRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemService {

    private ItemRepository repository;

    @PostConstruct
    public void initRepos() {
        repository = new ItemRepository();
    }

    public GetItemsResponse getAll(int userId) {
        if (userId != 0) {
            return new GetItemsResponse(repository.getAllByUserId(userId));
        }

        return new GetItemsResponse(repository.getAll());
    }

    public GetItemResponse getById(int id) {
        return new GetItemResponse(repository.getById(id));
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public CreateItemResponse create(CreateItemRequest request) {
        Item item = new Item();
        item.setUserId(request.getUserId());
        item.setName(request.getName());
        item.setDescription(request.getDescription());

        return new CreateItemResponse(repository.create(item)) ;
    }
}
