package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.dto.book.AddNewBookDTO;
import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.service.AuthorService;
import bg.softuni.onlinebookstore.service.BookService;
import bg.softuni.onlinebookstore.service.GenreService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public String getAllBooks(Model model,
                              @PageableDefault(
                                      sort = "title",
                                      direction = Sort.Direction.ASC,
                                      size = 5) Pageable pageable) {
        model.addAttribute("books", bookService.getAllBooks(pageable));
        model.addAttribute("title", "All books");
        model.addAttribute("type", "all");

        return "books";
    }

    @GetMapping("/{genre}")
    public String getBooksByGenre(@PathVariable("genre") String genre, Model model,
                                  @PageableDefault(
                                          sort = "title",
                                          direction = Sort.Direction.ASC,
                                          size = 5) Pageable pageable) {
        model.addAttribute("books", bookService.getBooksByGenre(genre, pageable));
        model.addAttribute("title", bookService.getGenreName(genre));
        model.addAttribute("type", genre);


        return "books";
    }

    @GetMapping("/{id}/details")
    public String getBookDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBookDetails(id));

        return "book-details";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        if (!model.containsAttribute("bookModel")) {
            model.addAttribute("bookModel", new AddNewBookDTO());
        }

        model.addAttribute("action", "/books/add");
        model.addAttribute("buttonText", "Add Book");
        model.addAttribute("title", "Add New Book");
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book-add-or-update";
    }

    @PostMapping("/add")
    public String addBook(@Valid AddNewBookDTO bookModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookModel", bookModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookModel",
                    bindingResult);
            return "redirect:/books/add";
        }

        BookEntity newBook = this.bookService.addNewBook(bookModel);

        return String.format("redirect:/books/%d/details", newBook.getId());
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model) {
        if (!model.containsAttribute("bookModel")) {
            model.addAttribute("bookModel", bookService.getBookById(id));
        }
        model.addAttribute("action", "/books/update/" + id);
        model.addAttribute("buttonText", "Update Book");
        model.addAttribute("title", "Update Book");
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book-add-or-update";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@Valid AddNewBookDTO bookModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookModel", bookModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookModel",
                    bindingResult);
            return "redirect:/books/update/{id}";
        }

        BookEntity updatedBook = this.bookService.updateBook(bookModel, id);

        return String.format("redirect:/books/%d/details", updatedBook.getId());
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/all";
    }
}
