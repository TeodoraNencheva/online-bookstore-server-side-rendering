package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.UserRoleEntity;
import bg.softuni.onlinebookstore.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByName(UserRoleEnum roleEnum);
}
