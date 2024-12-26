package cz.ales17.auto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String loginPage(Model m) {
        m.addAttribute("title", "Přihlášení");
        return "login";
    }
}
