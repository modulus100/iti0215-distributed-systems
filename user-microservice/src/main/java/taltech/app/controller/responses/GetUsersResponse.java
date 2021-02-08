package taltech.app.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import taltech.core.models.User;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class GetUsersResponse {
    private ArrayList<User> users;
}
