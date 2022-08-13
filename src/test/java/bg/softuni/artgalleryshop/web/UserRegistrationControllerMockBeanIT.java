package bg.softuni.artgalleryshop.web;

import bg.softuni.artgalleryshop.service.EmailService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerMockBeanIT {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private EmailService mockEmailService;

  @Test
  void testRegistrationPageShown() throws Exception {
    mockMvc.perform(get("/users/register")).
            andExpect(status().isOk()).
            andExpect(view().name("auth-register"));
  }

  @Test
  void testUserRegistration() throws Exception {
    mockMvc.perform(post("/users/register").
                    param("username", "TestUsername").
                    param("firstName", "Petar").
                    param("lastName", "Petrov").
                    param("email", "pesho@test.bg").
                    param("password", "123456").
                    param("confirmPassword", "123456").
                    cookie(new Cookie("lang", Locale.GERMAN.getLanguage())).
                    with(csrf())
            ).
            andExpect(status().is3xxRedirection()).
            andExpect(redirectedUrl("/"));

    verify(mockEmailService).
            sendRegistrationEmail("pesho@test.bg",
                    "Petar Petrov",
                    Locale.GERMAN);
  }
}
