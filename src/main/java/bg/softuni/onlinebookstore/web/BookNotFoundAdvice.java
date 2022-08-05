package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.error.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BookNotFoundAdvice {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({BookNotFoundException.class})
    public ModelAndView onBookNotFound(BookNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("book-not-found");
        modelAndView.addObject("bookId", ex.getId());

        return modelAndView;
    }
}
