package bg.softuni.artgalleryshop.exceptionHandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArtGalleryShopControllerAdvice {
    @ExceptionHandler({Exception.class})
    public String handleError() {
        return "redirect:error";
    }
}
