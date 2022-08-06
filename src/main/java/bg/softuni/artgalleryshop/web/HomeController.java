package bg.softuni.artgalleryshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/about-us")
    public String about() {
        return "about-us";
    }
}
