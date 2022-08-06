package bg.softuni.artgalleryshop.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserPasswordForgotDto {

    @Email(message = "User email should be valid.")
    @NotEmpty(message = "User email should be provided.")
    private String email;

    public UserPasswordForgotDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
