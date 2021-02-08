package taltech.app.controller.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import taltech.core.models.User;

@Data
@AllArgsConstructor
public class GetUserResponse {
    private User user;
}
