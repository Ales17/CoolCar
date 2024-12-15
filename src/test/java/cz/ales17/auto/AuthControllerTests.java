package cz.ales17.auto;

import cz.ales17.auto.controller.AuthController;
import cz.ales17.auto.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnLoginPage_whenAccessingLoginEndpoint() throws Exception {
        this.mockMvc
                .perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void shouldRedirectToLogin_whenAnonymousUserAccessesRoot() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().exists("Location"));
    }
}
