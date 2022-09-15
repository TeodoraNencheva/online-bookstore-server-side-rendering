package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
}
