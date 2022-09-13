package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.dto.author.AddNewAuthorDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorDetailsDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorOverviewDTO;
import bg.softuni.onlinebookstore.model.dto.book.BookOverviewDTO;
import bg.softuni.onlinebookstore.model.dto.search.SearchDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.model.error.AuthorNotFoundException;
import bg.softuni.onlinebookstore.service.AuthorService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAllAuthors(Model model,
                                @PageableDefault(
                                        sort = "lastName",
                                        direction = Sort.Direction.ASC,
                                        page = 0,
                                        size = 4) Pageable pageable) {
        model.addAttribute("authors", authorService.getAllAuthors(pageable));

        return "authors";
    }

    @GetMapping("/{id}")
    public String getAuthorDetails(@PathVariable("id") Long id, Model model) {
        AuthorDetailsDTO author = authorService.getAuthorDetails(id);

        if (author == null) {
            throw new AuthorNotFoundException(id);
        }

        model.addAttribute("author", author);
        return "author-details";
    }

    @GetMapping("/add")
    public String addAuthor(Model model) {
        if (!model.containsAttribute("authorModel")) {
            model.addAttribute("authorModel", new AddNewAuthorDTO());
        }

        model.addAttribute("title", "Add New Author");
        model.addAttribute("action", "/authors/add");
        model.addAttribute("buttonText", "Add Author");
        model.addAttribute("method", "post");

        return "author-add-or-update";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid AddNewAuthorDTO authorModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("authorModel", authorModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorModel",
                    bindingResult);
            return "redirect:/authors/add";
        }

        AuthorEntity newAuthor = authorService.addNewAuthor(authorModel);

        return String.format("redirect:/authors/%d", newAuthor.getId());
    }

    @GetMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") Long id, Model model) {
        AddNewAuthorDTO authorModel = authorService.getAuthorById(id);

        if (authorModel == null) {
            throw new AuthorNotFoundException(id);
        }

        if (!model.containsAttribute("authorModel")) {
            model.addAttribute("authorModel", authorModel);
        }

        model.addAttribute("title", "Update Author");
        model.addAttribute("action", "/authors/" + id);
        model.addAttribute("buttonText", "Update Author");
        model.addAttribute("method", "put");

        return "author-add-or-update";
    }

    @PutMapping("/{id}")
    public String updateAuthor(@Valid AddNewAuthorDTO authorModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("authorModel", authorModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorModel",
                    bindingResult);
            return "redirect:/authors/update/{id}";
        }

        AuthorEntity updatedAuthor = authorService.updateAuthor(authorModel, id);
        if (updatedAuthor == null) {
            throw new AuthorNotFoundException(id);
        }
        return String.format("redirect:/authors/%d", updatedAuthor.getId());
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        if (authorService.getAuthorById(id) == null) {
            throw new AuthorNotFoundException(id);
        }

        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({AuthorNotFoundException.class})
    public ModelAndView onAuthorNotFound(AuthorNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("title", "Author not found");
        modelAndView.addObject("message", String.format("Author with id %s not found", ex.getId()));

        return modelAndView;
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("title", "Search for an author");
        model.addAttribute("action", "/authors/search");

        if (!model.containsAttribute("searchDTO")) {
            model.addAttribute("searchDTO", new SearchDTO());
        }

        return "search";
    }

    @PostMapping("/search")
    public String search(Model model,
                         @Valid SearchDTO searchDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchDTO", searchDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.searchDTO",
                    bindingResult);
            return "redirect:/authors/search";
        }

        List<AuthorOverviewDTO> authors = authorService.searchAuthors(searchDTO);
        model.addAttribute("authors", authors);

        return "search";
    }
}
