package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>,
        JpaSpecificationExecutor<AuthorEntity> {
}
