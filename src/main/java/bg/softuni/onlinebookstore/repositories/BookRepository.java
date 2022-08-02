package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Page<BookEntity> getAllByGenre_Name(String genreName, Pageable pageable);

    List<BookEntity> getAllByAuthor_Id(Long id);

    void deleteAllByAuthor_Id(Long id);
}
