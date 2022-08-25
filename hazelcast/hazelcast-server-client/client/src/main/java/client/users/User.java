package client.users;

import javax.persistence.Id;
import java.util.List;

public class User {
    @Id
    private Long id;
    private String name;
    private List<Long> snapshoots;

}
