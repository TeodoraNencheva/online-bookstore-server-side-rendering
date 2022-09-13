package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.dto.author.AddNewAuthorDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorDetailsDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorNameDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorOverviewDTO;
import bg.softuni.onlinebookstore.model.dto.search.SearchDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.model.mapper.AuthorMapper;
import bg.softuni.onlinebookstore.repositories.AuthorRepository;
import bg.softuni.onlinebookstore.repositories.AuthorSpecification;
import bg.softuni.onlinebookstore.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
    }

    public Page<AuthorOverviewDTO> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(authorMapper::authorEntityToAuthorOverviewDTO);
    }

    public List<AuthorNameDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::authorEntityToAuthorNameDTO)
                .collect(Collectors.toList());
    }

    public AuthorDetailsDTO getAuthorDetails(Long id) {
        Optional<AuthorEntity> authorOpt = authorRepository.findById(id);
        if (authorOpt.isEmpty()) {
            return null;
        }

        return authorMapper.authorEntityToAuthorDetailsDTO(authorOpt.get());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AuthorEntity addNewAuthor(AddNewAuthorDTO authorModel) {
        AuthorEntity newAuthor = new AuthorEntity(authorModel);
        return authorRepository.save(newAuthor);
    }

    public AddNewAuthorDTO getAuthorById(Long id) {
        Optional<AuthorEntity> authorOpt = authorRepository.findById(id);
        if (authorOpt.isEmpty()) {
            return null;
        }

        return authorMapper.authorEntityToAddNewAuthorDTO(authorOpt.get());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AuthorEntity updateAuthor(AddNewAuthorDTO authorModel, Long id) {
        Optional<AuthorEntity> authorOpt = authorRepository.findById(id);
        if (authorOpt.isEmpty()) {
            return null;
        }

        AuthorEntity author = authorOpt.get();
        author.setFirstName(authorModel.getFirstName());
        author.setLastName(authorModel.getLastName());
        author.setBiography(authorModel.getBiography());
        author.setPhotoUrl(authorModel.getPhotoUrl());
        return authorRepository.save(author);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Transactional
    public void deleteAuthor(Long id) {
        bookRepository.deleteAllByAuthor_Id(id);
        authorRepository.deleteById(id);
    }

    public List<AuthorOverviewDTO> searchAuthors(SearchDTO searchDTO) {
        return this.authorRepository.findAll(new AuthorSpecification(searchDTO))
                .stream().map(authorMapper::authorEntityToAuthorOverviewDTO)
                .collect(Collectors.toList());
    }
}
