package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.dto.book.BookOverviewDTO;
import bg.softuni.onlinebookstore.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookOverviewDTO>> getBooksByAuthor(
            @RequestParam(value = "authorId") Long authorId) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorId));
    }
}
