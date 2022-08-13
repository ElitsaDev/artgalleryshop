package bg.softuni.artgalleryshop.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    public void adminAllUsers() throws Exception {
        mockMvc.perform(get("/admin/all-users"))
                .andExpect(status().is(200));
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    public void admin() throws Exception {
        mockMvc.perform(get("/admin/delete/{id}"))
                .andExpect(status().is(200));
    }
}
