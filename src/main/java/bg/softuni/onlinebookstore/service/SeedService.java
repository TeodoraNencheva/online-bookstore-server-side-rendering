package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.entity.UserEntity;
import bg.softuni.onlinebookstore.model.entity.UserRoleEntity;
import bg.softuni.onlinebookstore.model.enums.UserRoleEnum;
import bg.softuni.onlinebookstore.repositories.UserRepository;
import bg.softuni.onlinebookstore.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class SeedService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    public SeedService(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }
    
    public void seedUserRoles() {
        if (this.userRoleRepository.count() == 0) {
            for (UserRoleEnum roleEnum : UserRoleEnum.values()) {
                UserRoleEntity userRole = new UserRoleEntity(roleEnum);
                this.userRoleRepository.save(userRole);
            }
        }
    }

    @Transactional
    public void addAdmin() {
        UserRoleEntity adminRole = this.userRoleRepository.findByName(UserRoleEnum.ADMIN).get();
        UserEntity admin = this.userRepository.findById(1L).get();
        admin.addRole(adminRole);
        this.userRepository.save(admin);
    }
}
