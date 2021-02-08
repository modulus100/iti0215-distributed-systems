package taltech.app.requests;

import lombok.Data;

@Data
public class CreateItemRequest {

    private int userId;
    private String name;
    private String description;
}
