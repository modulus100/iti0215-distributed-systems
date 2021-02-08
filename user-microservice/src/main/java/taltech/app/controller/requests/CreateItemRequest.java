package taltech.app.controller.requests;

import lombok.Data;

@Data
public class CreateItemRequest {

    private int userId;
    private String name;
    private String description;
}
