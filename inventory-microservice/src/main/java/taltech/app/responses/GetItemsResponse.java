package taltech.app.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import taltech.core.models.Item;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class GetItemsResponse {

    private ArrayList<Item> items;
}
