package mg.rjc.testcars.service;

import mg.rjc.testcars.entities.AppRole;
import mg.rjc.testcars.entities.AppUser;

import java.util.List;

public interface AccountService {
    public AppUser registration(String name, String email, String username, String password, String confirmPassword);
    public AppRole saveRole(AppRole appRole);
    public void addRoleToUser(String username, String roleName);
    public AppUser getUser(String username);
    public List<AppUser> getUsers();
}
