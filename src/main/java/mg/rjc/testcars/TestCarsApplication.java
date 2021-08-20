package mg.rjc.testcars;

import mg.rjc.testcars.entities.AppRole;
import mg.rjc.testcars.entities.Car;
import mg.rjc.testcars.entities.Category;
import mg.rjc.testcars.service.AccountService;
import mg.rjc.testcars.service.CarService;
import mg.rjc.testcars.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TestCarsApplication{

    public static void main(String[] args) {
        SpringApplication.run(TestCarsApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    CommandLineRunner start(
            AccountService accountService,
            CategoryService categoryService,
            CarService carService) {
        return args -> {

            accountService.saveRole(new AppRole(null, "ROLE_USER"));
            accountService.saveRole(new AppRole(null, "ROLE_MANAGER"));
            accountService.saveRole(new AppRole(null, "ROLE_ADMIN"));
            accountService.saveRole(new AppRole(null, "ROLE_SUPER_ADMIN"));

            accountService.registration("John Willy", "john@gmail.com", "john", "1234", "1234");
            accountService.registration("James Smith", "james@gmail.com", "james", "1234", "1234");
            accountService.registration("Claude Pierre", "claude@gmail.com", "claude", "1234", "1234");
            accountService.registration("Jacques Dupond", "jacques@gmail.com", "jacques", "1234", "1234");

            accountService.addRoleToUser("james", "ROLE_MANAGER");

            accountService.addRoleToUser("claude", "ROLE_ADMIN");
            accountService.addRoleToUser("claude", "ROLE_SUPER_ADMIN");

            categoryService.saveCategory(new Category(null, "Compacte"));
            categoryService.saveCategory(new Category(null, "Berline"));
            categoryService.saveCategory(new Category(null, "Cabriolet"));
            categoryService.saveCategory(new Category(null, "Break"));
            categoryService.saveCategory(new Category(null, "4x4"));
            categoryService.saveCategory(new Category(null, "Monospace"));

            Category category = categoryService.getCategoryById(6L);

            carService.saveCar(new Car(null, "TEST-457878", "Renault", "Clio", "Rouge", "2018","TEST-457878.png", category));
            carService.saveCar(new Car(null, "TEST-201545", "Renault", "Clio", "Blanc", "2002","TEST-201545", category));

        };
    }

}
