package mg.rjc.testcars.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.rjc.testcars.entities.AppRole;
import mg.rjc.testcars.entities.AppUser;
import mg.rjc.testcars.repository.AppRoleRepository;
import mg.rjc.testcars.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if ( appUser == null ) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(appRole -> authorities.add(new SimpleGrantedAuthority(appRole.getRoleName())));
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser registration(String name, String email, String username, String password, String confirmPassword) {
        AppUser user = userRepository.findByUsername(username);
        if ( user != null ) {
            log.error("User {} already exists", username);
            throw new RuntimeException("User " + username + " already exists");
        }
        if ( !password.equals(confirmPassword) ) {
            log.error("Please confirm your password");
            throw new RuntimeException("Please confirm your password");
        }
        AppUser appUser = new AppUser();
        appUser.setName(name);
        appUser.setEmail(email);
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(confirmPassword));
        appUser.setActive(true);
        log.info("Saving new user {} to the database", username);
        userRepository.save(appUser);
        addRoleToUser(username, "ROLE_USER");
        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        log.info("Saving new role {} to the database", appRole.getRoleName());
        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByRoleName(roleName);
        log.info("Adding role {} to user {}", roleName, username);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

}
