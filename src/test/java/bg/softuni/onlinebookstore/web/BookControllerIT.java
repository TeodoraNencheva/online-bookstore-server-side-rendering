package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.dto.book.BookOverviewDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testAllBooksPageShown() throws Exception {
        AuthorEntity author = new AuthorEntity("Astrid", "Lindgren", "short biography", "photo URL");
        when(bookService.getAllBooks(PageRequest.of(0, 5,
                Sort.by("title"))))
                .thenReturn(new PageImpl<>(List.of(
                        new BookOverviewDTO(1L, "Pippi Longstocking", author, "FOR CHILDREN", "photo URL 1"),
                        new BookOverviewDTO(2L, "Karlson", author, "FOR CHILDREN", "photo URL 2")
                )));

        mockMvc.perform(get("/books/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"));
    }
}
