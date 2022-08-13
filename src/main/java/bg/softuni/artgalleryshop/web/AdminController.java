package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.model.view.UserViewModel;
import bg.softuni.artgalleryshop.service.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public String allUsers(Model model) {
        List<UserViewModel> users = this.userService.findAll();

        model.addAttribute("users", users);
        return "all-users";
    }

    @PostMapping("/all-users")
    public String inputSearchUser(@RequestParam String text, Model model) {
        List<UserViewModel> users = this.userService.findByNameCriteria(text);

        model.addAttribute("users", users);
        model.addAttribute("text", text);
        return "all-users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return "redirect:/admin/all-users";
    }

    @GetMapping("/change/{id}")
    public String changeUserRole(@PathVariable Long id) throws Exception {
        this.userService.changeCurrentUserRole(id);
        return "redirect:/admin/all-users";
    }
}
