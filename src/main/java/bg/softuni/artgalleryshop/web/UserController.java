package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.model.view.ProductViewModel;
import bg.softuni.artgalleryshop.service.ProductService;
import bg.softuni.artgalleryshop.service.StatisticService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final StatisticService statisticService;
    private final ProductService productService;

    public UserController(StatisticService statisticService, ProductService productService) {
        this.statisticService = statisticService;
        this.productService = productService;
    }

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }

  @PostMapping("/login-error")
  public String onFailedLogin(
      @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
      RedirectAttributes redirectAttributes
  ) {

    redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
    redirectAttributes.addFlashAttribute("bad_credentials", true);

    return "redirect:/users/login";
  }
  
  
  @GetMapping("/products")
    public String products(Model model, HttpServletResponse response) {
        List<ProductViewModel> productViewModels = this.productService.findAll();
        model.addAttribute("products", productViewModels);
        this.statisticService.saveVisitationInDataBase(response.getHeader("visitor"));

        return "products";
    }
}
