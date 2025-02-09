package cz.ales17.auto;

import cz.ales17.auto.controller.AuthController;
import cz.ales17.auto.entity.Role;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder encoder;
    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private AuthController authController;

    @BeforeEach
    void init() {
        UserEntity testUser = new UserEntity();
        testUser.setUsername("user1");
        testUser.setPassword(encoder.encode("password"));

        Role user = Role.builder()
                .id((byte)1)
                .name("USER")
                .build();
        Set<Role> roles = Set.of(user);
        testUser.setRoles(roles);

        when(userDetailsService.loadUserByUsername("user1")).thenReturn(testUser);
    }


    @Test
    void shouldLoginSuccessfullyWithValidCredentials() throws Exception {
        mockMvc.perform(formLogin("/login").user("user1").password("password")).andExpect(redirectedUrl("/"));
    }


    @Test
    void shouldFailToLoginWithInvalidPassword() throws Exception {
        mockMvc.perform(formLogin("/login").user("user1").password("invalidpassword")).andExpect(redirectedUrl("/login?error"));
    }


    @Test
    void shouldReturnLoginPage_whenAccessingLoginEndpoint() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
    }

    @Test
    void shouldRedirectToLogin_whenAnonymousUserAccessesRoot() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andExpect(header().string("Location", "http://localhost/login"));
    }
}
