package bg.softuni.onlinebookstore.util;

import bg.softuni.onlinebookstore.model.entity.UserEntity;
import bg.softuni.onlinebookstore.model.entity.UserRoleEntity;
import bg.softuni.onlinebookstore.model.enums.UserRoleEnum;
import bg.softuni.onlinebookstore.repositories.UserRepository;
import bg.softuni.onlinebookstore.repositories.UserRoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class TestDataUtils {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    public TestDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity("Admin", "Adminov", "admin@example.com", "adminPass",
                new HashSet<>(userRoleRepository.findAll()));

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity("User", "Userov", "user@example.com", "userPass",
                userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getName() != UserRoleEnum.ADMIN).
                        collect(Collectors.toSet()));

        return userRepository.save(user);
    }

    public void cleanUpDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }
}
