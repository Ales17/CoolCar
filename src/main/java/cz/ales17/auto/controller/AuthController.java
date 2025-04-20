package cz.ales17.auto.controller;

import cz.ales17.auto.dto.PasswordChangeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("/login")
    public String loginPage(Model m) {
        m.addAttribute("title", "Přihlášení");
        return "login";
    }

}
