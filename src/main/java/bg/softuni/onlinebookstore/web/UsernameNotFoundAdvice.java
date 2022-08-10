package bg.softuni.onlinebookstore.web;

import bg.softuni.onlinebookstore.model.error.GenreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UsernameNotFoundAdvice {
    @ExceptionHandler({UsernameNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView onUsernameNotFound(UsernameNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("username-not-found");
        modelAndView.addObject("username", ex.getMessage());

        return modelAndView;
    }
}
