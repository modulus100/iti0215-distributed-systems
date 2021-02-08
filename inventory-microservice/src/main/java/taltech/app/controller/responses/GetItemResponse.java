package taltech.app.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import taltech.core.models.Item;

@Data
@AllArgsConstructor
public class GetItemResponse {

    private Item item;
}
