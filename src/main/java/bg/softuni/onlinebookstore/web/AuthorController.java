package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.dto.author.AddNewAuthorDTO;
import bg.softuni.onlinebookstore.model.dto.book.AddNewBookDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.service.AuthorService;
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
        model.addAttribute("author", authorService.getAuthorDetails(id));

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
        if (!model.containsAttribute("authorModel")) {
            model.addAttribute("authorModel", authorService.getAuthorById(id));
        }

        model.addAttribute("title", "Update Author");
        model.addAttribute("action", "/authors/update/" + id);
        model.addAttribute("buttonText", "Update Author");

        return "author-add-or-update";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@Valid AddNewAuthorDTO authorModel,
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

        return String.format("redirect:/authors/%d", updatedAuthor.getId());
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}
