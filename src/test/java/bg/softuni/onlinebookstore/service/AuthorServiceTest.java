package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.dto.author.AddNewAuthorDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.model.mapper.AuthorMapper;
import bg.softuni.onlinebookstore.repositories.AuthorRepository;
import bg.softuni.onlinebookstore.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorMapper authorMapper;

    private AuthorService toTest;

    @BeforeEach
    void setUp() {
        toTest = new AuthorService(authorRepository, bookRepository, authorMapper);
    }

    @Test
    void testGetAuthorById_AuthorExists() {
        AuthorEntity testAuthorEntity = new AuthorEntity();
        testAuthorEntity.setId(1L);
        testAuthorEntity.setFirstName("Test");
        testAuthorEntity.setLastName("Author");
        testAuthorEntity.setBiography("some author biography");
        testAuthorEntity.setPhotoUrl("photo img ulr");

        when(authorMapper.authorEntityToAddNewAuthorDTO(testAuthorEntity))
                .thenReturn(new AddNewAuthorDTO(testAuthorEntity.getFirstName(),
                        testAuthorEntity.getLastName(), testAuthorEntity.getBiography(),
                        testAuthorEntity.getPhotoUrl()));

        when(authorRepository.findById(testAuthorEntity.getId()))
                .thenReturn(Optional.of(testAuthorEntity));

        AddNewAuthorDTO author = toTest.getAuthorById(testAuthorEntity.getId());

        Assertions.assertEquals(testAuthorEntity.getFirstName(), author.getFirstName());
        Assertions.assertEquals(testAuthorEntity.getLastName(), author.getLastName());
        Assertions.assertEquals(testAuthorEntity.getBiography(), author.getBiography());
        Assertions.assertEquals(testAuthorEntity.getPhotoUrl(), author.getPhotoUrl());
    }
}
