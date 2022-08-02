package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.dto.author.AddNewAuthorDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorDetailsDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorNameDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorOverviewDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.model.mapper.AuthorMapper;
import bg.softuni.onlinebookstore.repositories.AuthorRepository;
import bg.softuni.onlinebookstore.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
    }

    public Page<AuthorOverviewDTO> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(this::authorEntityToAuthorOverviewDTO);
    }

    public List<AuthorNameDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::authorEntityToAuthorNameDTO)
                .collect(Collectors.toList());
    }

    //todo change to use mapper
    private AuthorOverviewDTO authorEntityToAuthorOverviewDTO(AuthorEntity author) {
        return new AuthorOverviewDTO(author.getId(), author.getFullName(), author.getPhotoUrl());
    }

    public AuthorDetailsDTO getAuthorDetails(Long id) {
        AuthorEntity author = authorRepository.findById(id).get();

        return new AuthorDetailsDTO(author.getId(), author.getFullName(),
                author.getBiography(), author.getPhotoUrl());
    }

    public AuthorEntity addNewAuthor(AddNewAuthorDTO authorModel) {
        AuthorEntity newAuthor = new AuthorEntity(authorModel);
        return authorRepository.save(newAuthor);
    }

    public AddNewAuthorDTO getAuthorById(Long id) {
        return authorMapper.authorEntityToAddNewAuthorDTO(authorRepository.findById(id).get());
    }

    public AuthorEntity updateAuthor(AddNewAuthorDTO authorModel, Long id) {
        AuthorEntity author = authorRepository.findById(id).get();
        author.setFirstName(authorModel.getFirstName());
        author.setLastName(authorModel.getLastName());
        author.setBiography(authorModel.getBiography());
        author.setPhotoUrl(authorModel.getPhotoUrl());
        return authorRepository.save(author);
    }

    @Transactional
    public void deleteAuthor(Long id) {
        bookRepository.deleteAllByAuthor_Id(id);
        authorRepository.deleteById(id);
    }
}
