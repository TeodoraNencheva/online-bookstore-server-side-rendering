package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.dto.author.AuthorDetailsDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorOverviewDTO;
import bg.softuni.onlinebookstore.service.AuthorService;
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
public class AuthorControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    void testAllAuthorsPageShown() throws Exception {
        when(authorService.getAllAuthors(PageRequest.of(0, 4,
                Sort.by("lastName"))))
                .thenReturn(new PageImpl<>(List.of(
                        new AuthorOverviewDTO(1L, "Astrid Lindgren", "Astrid photo"),
                        new AuthorOverviewDTO(2L, "John Steinbeck", "John Steinbeck photo")
                )));

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"));
    }

    @Test
    void testAuthorDetailsPageShown() throws Exception {
        AuthorDetailsDTO authorModel = new AuthorDetailsDTO(1L, "Astrid Lindgren", "short biography",
                "photo URL");
        when(authorService.getAuthorDetails(1L))
                .thenReturn(authorModel);

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("author-details"))
                .andExpect(model().attribute("author", authorModel));
    }
}
