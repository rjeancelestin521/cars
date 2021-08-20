package mg.rjc.testcars.forms;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}
