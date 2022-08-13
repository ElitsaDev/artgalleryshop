package bg.softuni.artgalleryshop.model.dto;

import javax.validation.constraints.NotEmpty;

public class UserNameDTO {
    @NotEmpty(message = "Username should be provided.")
    private String username;

    public UserNameDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
