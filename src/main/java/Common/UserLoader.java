package Common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserLoader {
    public static List<Users.User> loadUsers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Users users = mapper.readValue(new File("src/test/resources/users.json"), Users.class);
        return users.getUsers();
    }
}
