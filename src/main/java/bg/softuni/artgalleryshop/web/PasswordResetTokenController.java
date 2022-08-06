package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.model.dto.UserPasswordForgotDto;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.service.EmailService;
import bg.softuni.artgalleryshop.service.UserSecurityService;
import bg.softuni.artgalleryshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/password")
public class PasswordResetTokenController {

    private final UserSecurityService userSecurityService;
    private final UserService userService;
    private final EmailService emailService;


    public PasswordResetTokenController(UserSecurityService userSecurityService,
                                        UserService userService,
                                        EmailService emailService) {
        this.userSecurityService = userSecurityService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @ModelAttribute("forgotPasswordForm")
    public UserPasswordForgotDto forgotPasswordDto() {
        return new UserPasswordForgotDto();
    }
    @GetMapping("/forgetPassword")
    public String forgetPass(){
        return "forgetPassword";
    }

    @PostMapping()
    public String resetPassword(@Valid UserPasswordForgotDto forgotPasswordForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                HttpServletRequest request) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("forgotPasswordForm", forgotPasswordForm);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.forgotPasswordForm",
                    bindingResult);
            return "redirect:/forgetPassword";
        }

        UserEntity user = userService.findUserByEmail(forgotPasswordForm.getEmail());
        if (user == null) {
            return "redirect:/forgetPassword";
        }

        String token = UUID.randomUUID().toString();
        userSecurityService.createPasswordResetTokenForUser(token,user);

        emailService.sendResetPassEmail(user.getEmail(), token);
        request.isSecure();
        return "redirect:/resetPassword";
    }
}
