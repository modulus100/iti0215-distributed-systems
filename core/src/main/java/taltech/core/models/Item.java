package taltech.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    private int id;
    private int userId;
    private String name;
    private String Description;
}
