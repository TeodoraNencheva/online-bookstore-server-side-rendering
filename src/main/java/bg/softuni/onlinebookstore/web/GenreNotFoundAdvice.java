package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.error.GenreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GenreNotFoundAdvice {
    @ExceptionHandler({GenreNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView onGenreNotFound(GenreNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("genre-not-found");
        modelAndView.addObject("genreName", ex.getName());

        return modelAndView;
    }
}
