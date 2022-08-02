package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
