package bg.softuni.onlinebookstore;

import bg.softuni.onlinebookstore.service.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    private final SeedService seedService;

    public Initializer(SeedService seedService) {
        this.seedService = seedService;
    }

    public void run(String... args) throws Exception {
        this.seedService.seedUserRoles();
        this.seedService.addAdmin();
    }
}
