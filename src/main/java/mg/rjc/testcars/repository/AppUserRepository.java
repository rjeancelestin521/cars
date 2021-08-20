package mg.rjc.testcars.repository;

import mg.rjc.testcars.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
}
