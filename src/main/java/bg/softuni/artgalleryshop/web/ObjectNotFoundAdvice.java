package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFoundAdvice {
    @ExceptionHandler({ObjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView onProductNotFound(ObjectNotFoundException onfe) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("objectId", onfe.getObjectId());

        return modelAndView;

    }
}
