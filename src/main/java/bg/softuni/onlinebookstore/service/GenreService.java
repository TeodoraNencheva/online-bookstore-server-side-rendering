package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.entity.GenreEntity;
import bg.softuni.onlinebookstore.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreEntity> getAllGenres() {
        return genreRepository.findAll();
    }
}
