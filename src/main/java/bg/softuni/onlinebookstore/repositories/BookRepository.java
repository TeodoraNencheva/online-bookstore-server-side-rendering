package bg.softuni.onlinebookstore.repositories;

import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.model.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>,
        JpaSpecificationExecutor<BookEntity> {
    Page<BookEntity> getAllByGenre(GenreEntity genre, Pageable pageable);

    void deleteAllByAuthor_Id(Long id);

    List<BookEntity> getAllByAuthor(AuthorEntity author);
}
