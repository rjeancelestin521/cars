package mg.rjc.testcars.controller;

import lombok.RequiredArgsConstructor;
import mg.rjc.testcars.entities.AppRole;
import mg.rjc.testcars.entities.AppUser;
import mg.rjc.testcars.forms.RoleToUserForm;
import mg.rjc.testcars.forms.UserForm;
import mg.rjc.testcars.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @GetMapping(path = "/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(accountService.getUsers());
    }

    @PostMapping(path = "/users/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody UserForm form) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        AppUser appUser = accountService.registration(
                form.getName(),
                form.getEmail(),
                form.getUsername(),
                form.getPassword(),
                form.getConfirmPassword()
        );
        return ResponseEntity.created(uri).body(appUser);
    }

    @PostMapping(path = "/roles/save")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole appRole) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/save").toUriString());
        return ResponseEntity.created(uri).body(accountService.saveRole(appRole));
    }

    @PostMapping(path = "/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        accountService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}
