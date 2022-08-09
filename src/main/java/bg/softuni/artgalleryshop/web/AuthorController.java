package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.service.AuthorService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/authors/all")
    public String allOffers(
            Model model) {

        model.addAttribute("authors", authorService.getAllAuthorsProducts());

        return "authors";
    }
}

